package com.tjeit.a20191221_04_pizzastorelistapp

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.tjeit.a20191221_04_pizzastorelistapp.datas.PizzaStore
import kotlinx.android.synthetic.main.activity_pizza_store_detail.*

class PizzaStoreDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        val store = intent.getSerializableExtra("store") as PizzaStore
        callBtn.setOnClickListener {

            val listener = object : PermissionListener {
                override fun onPermissionGranted() {

                    val uri = Uri.parse("tel:${store.phoneNum}")
                    val intent = Intent(Intent.ACTION_CALL, uri)
                    startActivity(intent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "전화 권한 설정 필요", Toast.LENGTH_SHORT).show()
                }

            }

            TedPermission.with(mContext)
                .setPermissionListener(listener)
                .setDeniedMessage("전화 권한을 설정해야 사용 가능합니다. 설정에서 체크해주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()

        }
    }

    override fun setValues() {

        val store = intent.getSerializableExtra("store") as PizzaStore

        storeNameTxt.text = store.storeName
        storePhoneTxt.text = store.phoneNum

        Glide.with(mContext).load(store.logoUrl).into(storeLogoImg)


    }

}
