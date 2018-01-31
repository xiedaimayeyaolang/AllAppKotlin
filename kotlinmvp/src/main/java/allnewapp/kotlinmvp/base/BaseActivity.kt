package allnewapp.kotlinmvp.base

import allnewapp.kotlinmvp.presenter.BasePresenter
import allnewapp.kotlinmvp.view.BaseView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger

abstract class BaseActivity<P : BasePresenter<BaseView>> : AppCompatActivity(),AnkoLogger {
    lateinit var mPresenter: P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
        mPresenter = createPresenter()
    }
    abstract fun getLayoutId(): Int
    abstract fun createPresenter(): P
    abstract fun initViews()
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unDisposed()
    }
}
