package com.ubaya.advweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ubaya.advweek4.R
import com.ubaya.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init { // init ini akan dijalankan saat constructor nya dijalankan
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null

        fun showNotification(title: String, content: String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId)
                .apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT

                    setAutoCancel(true)
                }

            // konfigurasi yg sdh dibuat di builder akan dipanggil di notif
            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001, builder.build())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // just() -> utk memancarakan/meng-emit data bisa string bisa lain2nya juga
        val observable = Observable.just("hellow", "world", "!!")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("Messages", "data captured: ${it.toString()}") },
                { Log.e("Messages", "error: ${it!!.message.toString()}") },
                { Log.d("Messages", "Completed") },
            )

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, true,
            getString(R.string.app_name), "App channel")


//        // SEMUA CODE DIBAWAH INI DPT DI SEDERHANAKAN MENJADI SATU BAGIAN DENGAN OBJECT observable
//        // ini adalah subscriber nya, yg nanti akan mendapat data yg dikirim oleh object observable
//        val observer = object: Observer<String> {
//            // memberi tahu bahwa si observer memulai subscribe ke si observable
//            override fun onSubscribe(d: Disposable) {
//                Log.d("Messages", "Start subscribe")
//            }
//            // func ini akan dipanggil jika ada data baru yg dipancarkan/di-emit oleh observable
//            // t: String itu berisi data yg di emit oleh observable
//            override fun onNext(t: String) {
//                Log.d("Messages", "data captured: ${t.toString()}")
//            }
//            // func yg dipanggil apabila terjadi suatu kesalahan
//            override fun onError(e: Throwable) {
//                Log.e("Messages", "error: ${e!!.message.toString()}")
//            }
//            // utk memberitahukan kalo si observable ini sdh finish (tdk meng-emit data lagi)
//            override fun onComplete() {
//                Log.d("Messages", "Completed")
//            }
//        }

//        // klo observable ini berhubungan dengan REST API Calls maka pake io()
//        // tpi kalo dipake menjalankan program yg kompleks misal nge-render
//        // atau download file yg gede maka pake newThread() saja
//        observable
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer) // di subscribe oleh siapa? oleh observer
    }
}