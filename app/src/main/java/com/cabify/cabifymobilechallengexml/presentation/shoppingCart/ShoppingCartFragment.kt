package com.cabify.cabifymobilechallengexml.presentation.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cabify.cabifymobilechallengexml.databinding.FragmentShoppingCartBinding
import com.cabify.cabifymobilechallengexml.presentation.models.ProductsVo
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.showPromotionInfo
import com.cabify.cabifymobilechallengexml.presentation.utils.extensions.toDiscountPriceFormat


class ShoppingCartFragment : Fragment() {

    private val viewModel: ShoppingCartViewModel by viewModels()
    private var viewBinding: FragmentShoppingCartBinding? = null
    private lateinit var selectedProducts: ProductsVo
    private val args: ShoppingCartFragmentArgs by navArgs()
    private var selectedProductsAdapter = SelectedProductsAdapter { promotion ->
        this.context?.showPromotionInfo(promotion)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentShoppingCartBinding.inflate(inflater, container, false)

        selectedProducts = args.selectedProducts

        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setPrices()
    }

    private fun setAdapter() = with(viewBinding?.shoppingCartRvProductsSelected) {
        this?.layoutManager = LinearLayoutManager(requireContext())
        selectedProductsAdapter.submitList(selectedProducts.products)
        this?.adapter = selectedProductsAdapter
    }

    private fun setPrices() = with(viewBinding) {
        this?.shoppingCartTvTotalValue?.text =
            selectedProducts.totalPrice.toDiscountPriceFormat(selectedProducts.discountPrice)
    }
}