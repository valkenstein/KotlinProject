package presentation.mvvm

import domain.usecase.UpdateUserProfileUseCase

class SupplementViewModel  constructor(
    private val UpdateUserUseCase: UpdateUserProfileUseCase,
) : BaseViewModel() {


    fun safe() {
        launchInVMScope {
            showLoading()
            UpdateUserUseCase(currentUser).collectSuccess {
                //SupplementUserFragmentDirections.actionSupplementUserFragmentToHomeFragment().emit()
                //popBackStack()
            }
        }
    }

    fun safe2() {
//        launchInVMScope {
//            UpdateUserUseCase(currentUser).collectSuccess {
////                SupplementUserVitrinaFragmentDirections.actionSupplementUserFragmentToHomeFragment()
////                    .emit()
//            }
//        }
    }

}