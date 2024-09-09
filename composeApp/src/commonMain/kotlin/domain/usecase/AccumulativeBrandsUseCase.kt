package domain.usecase

import domain.model.CategoryDom

import domain.base.FlowResultUseCase
import domain.mapper.HomeMapper
import network.repository.ContentHomeRepository

class AccumulativeBrandsUseCase constructor(
    private val catalogRepo: ContentHomeRepository,
    private val cartMapper: HomeMapper
) : FlowResultUseCase<List<CategoryDom>>() {
    override suspend fun retrieveData() = cartMapper.map{catalogRepo.getAccumulativeBrands()}
}