package com.groupec.cleanarchitecturesampleapp.core.data.model

import com.groupec.cleanarchitecturesampleapp.core.model.data.Product
import com.groupec.cleanarchitecturesampleapp.core.network.model.ProductItemResponse
import com.groupec.cleanarchitecturesampleapp.core.network.model.ProductResponse


fun ProductResponse.toProductList(): List<Product> {
    return products.map { it.toProduct() }
}

fun ProductItemResponse.toProduct(): Product {
    return Product(id = id, libelle = libelle, desc = desc)
}