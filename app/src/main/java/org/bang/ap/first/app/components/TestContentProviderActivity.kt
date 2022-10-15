package org.bang.ap.first.app.components

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_content_provider.*
import org.bang.ap.first.app.R

class TestContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_content_provider)

        initView()

        requestPermission()
    }

    private fun initView() {
        get_contact.setOnClickListener {
            getContacts()
        }

        update_contact.setOnClickListener {
            updateContact()
        }

        insert_contact.setOnClickListener {
            insertContact()
        }

        delete_contact.setOnClickListener {
            deleteContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 200) {
            if (permissions[0] == Manifest.permission.READ_CONTACTS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getContacts()
                    Toast.makeText(this, "读取通讯录权限授权成功", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "读取通讯录的权限被拒绝，程序将无法继续工作", Toast.LENGTH_LONG).show()
                }
            }
            if (permissions[1] == Manifest.permission.WRITE_CONTACTS) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "写入通讯录权限授权成功", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "写入通讯录的权限被拒绝，程序将无法继续工作", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getContacts() {
//        Toast.makeText(this, "getContacts", Toast.LENGTH_LONG).show()

        // 1. 查询raw_contacts表获取联系人id
        val resolver = contentResolver
        val uri = Uri.parse("content://com.android.contacts/data/phones")
        // 2. 查询联系人数据
        val cursor = resolver.query(uri, null, null, null, null) ?: return
        while (cursor.moveToNext()) {
            // 获取联系人姓名、手机号码
            val displayName = cursor.getString(cursor.getColumnIndex("display_name"))
            val phoneNumber = cursor.getString(cursor.getColumnIndex("data1"))
            Log.e("ContentProvider", "姓名: $displayName")
            Log.e("ContentProvider", "号码: $phoneNumber")
            Log.e("ContentProvider", "====================")
        }
        cursor.close()
    }

    private fun updateContact() {
        val contactId = getContactIdByPhone(18888888888)
        if (contactId.isBlank()) {
            Toast.makeText(this, "联系人不存在，无法更新", Toast.LENGTH_LONG).show()
            return
        }
        val values = ContentValues()
        values.put(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
        values.put(StructuredName.GIVEN_NAME, "张三")
        val ret = contentResolver.update(
            ContactsContract.Data.CONTENT_URI,
            values,
            "${ContactsContract.Data.CONTACT_ID}=?",
            arrayOf(contactId)
        )
        if (ret > 0) {
            Toast.makeText(this, "更新成功", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "更新失败", Toast.LENGTH_LONG).show()
        }
    }

    private fun getContactIdByPhone(phone: Long): String {
        val uri = Uri.parse("content://com.android.contacts/data/phones/filter/$phone")
        val cursor =
            contentResolver.query(
                uri,
                arrayOf(ContactsContract.Data.CONTACT_ID),
                null,
                null,
                null
            )!!
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID))
        }
        return ""
    }

    private fun insertContact() {
        val values = ContentValues()

        /**
         * 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
         * 这时后面插入data表的数据，才能使插入的联系人在通讯录里面可见
         * */
        val rawContactUri =
            contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)!!
        val rawContractId = ContentUris.parseId(rawContactUri)

        // 往data表里面插入姓名数据
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContractId)
        values.put(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE) // 内容类型
        values.put(StructuredName.GIVEN_NAME, "李四")
        var ret = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        if (ret == null) {
            Toast.makeText(this, "姓名插入失败", Toast.LENGTH_LONG).show()
            return
        }

        // 往data表里面插入电话数据
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContractId)
        values.put(
            ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
        ) // 内容类型
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "18888888888")
        values.put(
            ContactsContract.CommonDataKinds.Phone.TYPE,
            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
        )
        ret = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        if (ret == null) {
            Toast.makeText(this, "手机号插入失败", Toast.LENGTH_LONG).show()
            return
        }

        // 往data表里面插入邮箱
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContractId)
        values.put(
            ContactsContract.Data.MIMETYPE,
            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
        ) // 内容类型
        values.put(ContactsContract.CommonDataKinds.Email.DATA, "1179034681@qq.com")
        values.put(
            ContactsContract.CommonDataKinds.Email.TYPE,
            ContactsContract.CommonDataKinds.Email.TYPE_WORK
        )
        ret = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        if (ret == null) {
            Toast.makeText(this, "邮箱插入失败", Toast.LENGTH_LONG).show()
            return
        } else {
            Toast.makeText(this, "插入联系人成功", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteContact() {
        val ret = contentResolver.delete(
            ContactsContract.RawContacts.CONTENT_URI,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?",
            arrayOf("张三")
        )
//        val ret = contentResolver.delete(
//            ContactsContract.RawContacts.CONTENT_URI,
//            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
//            arrayOf("张三")
//        )
        if (ret > 0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "删除失败", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestPermission() {
        val readable = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED

        val writable = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED

        if (readable || writable) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                200
            )

            //            if (!ActivityCompat.shouldShowRequestPermissionRationale(
            //                    this,
            //                    android.Manifest.permission.READ_CONTACTS
            //                )
            //            ) {
            //                // 在这里弹框引导用户去设置页打开读取联系人的权限
            //            }
        } else {
//            getContacts()
        }
    }
}
