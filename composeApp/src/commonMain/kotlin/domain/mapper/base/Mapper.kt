package domain.mapper.base

interface Mapper<Source, Target> {
   suspend fun map(src: Source): Target
}

interface ReverseMapper<Source, Target> : Mapper<Source, Target> {
    fun reverseMap(target: Target): Source
}