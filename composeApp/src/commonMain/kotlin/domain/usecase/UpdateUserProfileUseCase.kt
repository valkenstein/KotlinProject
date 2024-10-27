package domain.usecase

import domain.base.FlowResultWithParamsUseCase
import domain.mapper.PhoneModelMapper
import domain.model.UserOrderDom
import network.repository.ProfileRepository

class UpdateUserProfileUseCase constructor(
    private val profileRepository: ProfileRepository,
    private val orderUserInfoMapper: PhoneModelMapper

) : FlowResultWithParamsUseCase<UserOrderDom, String>() {
    override suspend fun retrieveData(params: UserOrderDom) = orderUserInfoMapper.map(
        { profileRepository.updateUserProfile(fillData(params)) }
    )

    fun fillData(params: UserOrderDom) =
        mapOf(
            checkEmpty("id", params.id),
            checkEmpty("firstName", params.firstName),
            checkEmpty("lastName", params.lastName),
            checkEmpty("secondName", params.secondName),
            checkEmpty("email", params.email),
            checkEmpty(
                "isSubscribe",
                if (params.isSubscribe) params.isSubscribe.toString() else ""
            )
        )

    private fun checkEmpty(key: String, value: String?): Pair<String, String> {
        if (value.isNullOrEmpty()) return "" to ""
        return key to value
    }
}