package com.eds.univo.prograiv.parcial3.modelos


class Persona {
    var id: Int? = null
    var name: String? = null
    var phoneNumber: String? = null
    var DUI: String? = null

    constructor(id: Int, name: String, phoneNumber: String, DUI: String){
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
        this.DUI = DUI
    }
}