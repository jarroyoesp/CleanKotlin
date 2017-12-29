package es.jarroyo.daggerandkotlin.domain.model

class User(var id: String,
           var name: String,
           var surname: String,
           var photo: String,
           var email: String,
           var password: String? = null)