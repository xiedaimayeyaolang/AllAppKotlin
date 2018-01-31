package allnewapp.kotlinmvp.act.Login

import allnewapp.kotlinmvp.bean.BaseModel
import allnewapp.kotlinmvp.view.BaseView

/**
 * Created by heping on 2018/1/31.
 */
interface LoginView<T : BaseModel>: BaseView{
    fun onSuccess(model: T)
    fun onFaild(error: String)
}
