package es.jarroyo.daggerandkotlin.data.mapper

import es.jarroyo.daggerandkotlin.data.exception.MapperException


internal interface Mapper<in I, out O> {

    @Throws(MapperException::class)
    fun map(input: I): O
}
