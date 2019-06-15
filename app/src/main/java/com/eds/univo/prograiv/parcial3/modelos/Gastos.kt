package com.eds.univo.prograiv.parcial3.modelos


class Gastos {
    var id: Int? = null
    var name: String? = null
    var amount: String? = null
    var price: String? = null

    constructor(id: Int, name: String, amount: String, price: String) {
        this.id = id
        this.name = name
        this.amount = amount
        this.price = price
    }
}