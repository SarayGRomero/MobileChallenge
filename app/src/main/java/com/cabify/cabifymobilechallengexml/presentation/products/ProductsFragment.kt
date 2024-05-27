package com.cabify.cabifymobilechallengexml.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cabify.cabifymobilechallengexml.R
import com.cabify.cabifymobilechallengexml.databinding.FragmentProductsBinding
import com.cabify.cabifymobilechallengexml.presentation.models.CabifyProductVo
import com.cabify.cabifymobilechallengexml.presentation.models.ProductsVo
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.showPromotionInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private var viewBinding: FragmentProductsBinding? = null
    private lateinit var productList: List<CabifyProductVo>
    private lateinit var selectedProducts: ProductsVo

    private var productsAdapter = ProductsAdapter({ products ->
        updateTotalCount(products)
    }, { promotion ->
        this.context?.showPromotionInfo(promotion)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProductsBinding.inflate(inflater, container, false)

        getProductsState()
        getTotalCountState()
        getSelectedProductsState()
        getNavigateState()

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()
        setAdapter()
        setShowCartButton()
    }

    private fun setAdapter() = with(viewBinding?.productsRvList) {
        this?.layoutManager = GridLayoutManager(requireContext(), 2)
        this?.adapter = productsAdapter
    }

    private fun setShowCartButton() = with(viewBinding?.productsBtnShowCart) {
        updateTotalCount(productList)
        this?.setOnClickListener {
            viewModel.updateSelectedProductsList(productList)
        }
    }

    private fun getProductsState() = lifecycleScope.launch {
        viewModel.products.collect { products ->
            productList = products
            productsAdapter.submitList(products)
        }
    }

    private fun updateTotalCount(updatedList: List<CabifyProductVo>) = lifecycleScope.launch {
        viewModel.updateTotalCount(updatedList)
    }

    private fun updateShowCartButton(totalCount: Int) = with(viewBinding?.productsBtnShowCart) {
        this?.visibility = if (totalCount > 0) View.VISIBLE else View.GONE
        this?.text = getString(R.string.products_show_cart, totalCount)
    }

    private fun getTotalCountState() = lifecycleScope.launch {
        viewModel.totalCount.collect { totalCount ->
            updateShowCartButton(totalCount)
        }
    }

    private fun getSelectedProductsState() = lifecycleScope.launch {
        viewModel.selectedProducts.collect { selectedProducts ->
            if (selectedProducts != null) {
                this@ProductsFragment.selectedProducts = selectedProducts
            } else {
                // TODO: Show error
            }
        }
    }

    private fun getNavigateState() = lifecycleScope.launch {
        viewModel.navigate.collect { navigate ->
            if (navigate) {
                val action =
                    ProductsFragmentDirections.actionProductsFragmentToShoppingCartFragment(selectedProducts)
                findNavController().navigate(action)
            }
            viewModel.updateNavigate(false)
        }
    }
}