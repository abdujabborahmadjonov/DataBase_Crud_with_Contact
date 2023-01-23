package dev.abdujabbor.db_browser.model

import java.io.Serializable

class Uset: Serializable {
    var id: Int? = null
    var name: String? = null
    var number: String? = null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }

    constructor()

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }


}