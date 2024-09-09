package presentation.mvvm

import androidx.compose.ui.node.Ref
import domain.model.CategoryDom
import domain.usecase.AccumulativeBrandsUseCase
import domain.usecase.AccumulativeStatusUseCase
import domain.model.MapKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LevelInformationViewModel constructor(
    private val accumulativeBrandsUseCase: AccumulativeBrandsUseCase,
    private val accumulativeStatusUseCase: AccumulativeStatusUseCase,
) : BaseViewModel() {
    private var selectedLevel = "base"
    private val listCategory = mutableListOf<CategoryDom>()
    private val _dataItems =
        MutableStateFlow<Map<String, List<CategoryDom>>>(emptyMap<String, List<CategoryDom>>())
    val dataItems = _dataItems.asStateFlow()

    private val _dropDownItems = MutableStateFlow<List<MapKey>>(emptyList())
    val dropDownItems = _dropDownItems.asStateFlow()

    private var currentCategory: List<CategoryDom> = emptyList()
    private val digitsRegex = Regex("[0-9]")
    private var query: String = ""
    private var currentSelectedDropDown: MapKey = MapKey(key = selectedLevel)


    fun getSelectedLevel() = selectedLevel

    init {
        init()
    }

    private fun init() {
        getBrandsAsync()
        getStatusAsync()
    }

    fun selectDropDown(mapKey: MapKey) {
        currentSelectedDropDown = mapKey
        query?.let { queryChange(it) }
    }


    private fun getStatusAsync() {
        launchInVMScope {
            showLoading()
            accumulativeStatusUseCase().collectSuccess {
                _dropDownItems.emit(it.map {
                    MapKey(
                        key = it.id,
                        value = it.title,
                        img = it.image,
                    )
                })
            }
        }
    }

    private fun getBrandsAsync() {
        launchInVMScope {
            showLoading()
            accumulativeBrandsUseCase().collectSuccess {
                //listCategory.clear()
                //sectionSearchItems.clear()
                //listCategory.addAll(groupCategory(it))
                currentCategory = it
                _dataItems.emit(groupCategory(it))
            }
        }
    }

    fun queryChange(q: String) {
        query = q
        val s = currentCategory.filter { it.title.contains(q, true) }.sortedBy { it.title }
        launchMain {
            _dataItems.emit(groupCategory(s))
            //sectionSearchItems.update(searchItems(listCategory))
            //_dataItems.postValue(createBrandItems(listCategory))
        }
    }


    private fun groupCategory(brands: List<CategoryDom>) =
        brands.filter { it.discounts[currentSelectedDropDown?.key] != "0" }
            .groupBy { brand ->
                val name = brand.title
                if (name.isEmpty()) {
                    ""
                } else {
                    val firstLetter =
                        name.first().toString().toUpperCase()
                    if (firstLetter.matches(digitsRegex)) {
                        "0-9"
                    } else {
                        firstLetter
                    }
                }
            }.toMap()


    private fun openWebBrands(category: CategoryDom) {
//        BrandsFragmentDirections.actionBrandsFragmentToWebViewFragment(
//            WebViewParam(category.url)
//        ).emit()
    }

    companion object {
        private val level = mapOf(
            "base" to "Базовый уровень",
            "silver" to "Серебряный уровень",
            "gold" to "Золотой уровень",
            "platinum" to "Платиновый уровень",
        )

    }
}