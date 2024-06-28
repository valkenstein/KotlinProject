package org.example.project

import App
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import presentation.component.AppProject
import presentation.component.bonus.ItemBonusAccount

class MainActivity : ComponentActivity() {
    //private val bonusOperationUseCase: BonusOperationUseCase by inject()
    private var scope = CoroutineScope(Dispatchers.Main + SupervisorJob())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        KoinInjector.koinApp.androidContext(this)
//        Log.e("bonusOperationUseCase11", bonusOperationUseCase.toString())
//        println(bonusOperationUseCase)
       // getBonus()
        setContent {
            AppProject(false, false)

        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

//    private fun getBonus() {
//        scope.launch {
//            try {
//                val r = bonusOperationUseCase.getBonus()
//                println(r)
//            } catch (e: Exception) {
//                println(e.printStackTrace())
//
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    ItemBonusAccount()
}