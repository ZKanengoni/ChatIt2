package com.example.chatit.ModelClasses

class Users {
    private var cover: String = ""
    private var profile: String = ""
    private var search: String = ""
    private var status: String = ""
    private var uid: String = ""
    private var username: String = ""




    constructor()
    constructor(
        cover: String,
        profile: String,
        search: String,
        status: String,
        uid: String,
        username: String

    ) {
        this.uid = uid
        this.username = username
        this.profile = profile
        this.cover = cover
        this.search = search
        this.status = status
    }

    fun getUID(): String?{
        return uid
    }

    fun  setUID(uid: String){
        this.uid = uid
    }

    fun getUserName(): String?{
        return username
    }

    fun  setUserName(username: String){
        this.username = username
    }

    fun getProfile(): String?{
        return profile
    }

    fun  setProfile(profile: String){
        this.profile = profile
    }

    fun getCover(): String?{
        return cover
    }

    fun  setCover(cover: String){
        this.cover = cover
    }

    fun getSearch(): String?{
        return search
    }

    fun  setSearch(search: String){
        this.search = search
    }

    fun getStatus(): String?{
        return status
    }

    fun  setStatus(status: String){
        this.status = status
    }
}