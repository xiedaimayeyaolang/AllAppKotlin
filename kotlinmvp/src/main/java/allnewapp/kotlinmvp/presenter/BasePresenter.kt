package allnewapp.kotlinmvp.presenter

import allnewapp.kotlinmvp.bean.BaseModel
import allnewapp.kotlinmvp.net.ApiClient
import allnewapp.kotlinmvp.net.ApiStores
import allnewapp.kotlinmvp.view.BaseView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger

/**
 * Created by heping on 2018/1/31.
 */
abstract class BasePresenter<T : BaseView> (val mView: T): AnkoLogger{
    val disposable: CompositeDisposable = CompositeDisposable()
    companion object{
        fun apiStoreInstance(): ApiStores? = ApiClient.retrofit()?.create(ApiStores::class.java)
    }
    protected  fun <T: BaseModel> addDisposable(observable: Observable<T>,
                                                onSuccess: Consumer<T>,
                                                onThrowable: Consumer<Throwable>) {
        disposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onThrowable))
    }
    protected  fun <T: BaseModel> addToDisposable(observable: Observable<T>,
                                                  consumer: Consumer<T>,
                                                  onThrowable: Consumer<Throwable>) {
        disposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, onThrowable))
    }
    fun unDisposed() = { if(disposable.isDisposed) disposable.dispose() }
}
