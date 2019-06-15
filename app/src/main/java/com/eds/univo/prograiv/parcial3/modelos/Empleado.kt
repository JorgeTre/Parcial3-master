package com.eds.univo.prograiv.parcial3.modelos


class Empleado {
    var id: Int? = null
    var name: String? = null
    var job: String? = null
    var DUI: String? = null

    constructor(id: Int, name: String, job: String, DUI: String){
        this.id = id
        this.name = name
        this.job = job
        this.DUI = DUI
    }
}