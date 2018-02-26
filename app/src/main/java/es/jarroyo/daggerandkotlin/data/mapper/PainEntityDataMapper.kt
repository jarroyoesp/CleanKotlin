package es.jarroyo.daggerandkotlin.data.mapper

import es.jarroyo.daggerandkotlin.data.entity.PainEntity
import es.jarroyo.daggerandkotlin.domain.model.BodyPart


class PainEntityDataMapper : Mapper<PainEntity, BodyPart> {

    fun mapList(painList: List<PainEntity>) : List<BodyPart>{
        var listPainMapped = mutableListOf<BodyPart>()
        for (painEntity in painList) {
            listPainMapped.add(map(painEntity))
        }
        return listPainMapped
    }

    override fun map(input: PainEntity): BodyPart =
            BodyPart(
                    getId(input),
                    getName(input),
                    getPainLevel(input),
                    getUserId(input))

    fun mapToEntity(input: BodyPart): PainEntity =
            PainEntity(
                    input.id,
                    input.name!!,
                    input.painLevel,
                    input.userId)

    private fun getId(input: PainEntity): String{
       return input.id
    }

    private fun getName(input: PainEntity) = input.name

    private fun getPainLevel(input: PainEntity) = input.painLevel

    private fun getUserId(input: PainEntity) = input.userId

}