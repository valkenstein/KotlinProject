package presentation.mvvm

import domain.model.UserOrderDom
import domain.usecase.UpdateUserProfileUseCase

class SupplementViewModel  constructor(
    private val UpdateUserUseCase: UpdateUserProfileUseCase,
) : BaseViewModel() {


    fun safe() {
        launchInVMScope {
            showLoading()
            UpdateUserUseCase(UserOrderDom()).collectSuccess {
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