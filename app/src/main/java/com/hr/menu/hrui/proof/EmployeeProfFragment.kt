package com.hr.menu.hrui.proof

import PreferenceManager
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hr.R
import com.hr.data.network.api.request.RequestEmployeeProfSubmition
import com.hr.data.network.api.request.RequestTables
import com.hr.data.network.api.response.ResponseTable
import com.hr.data.network.di.ImageConstants
import com.hr.data.network.di.showDialogToPick
import com.hr.data.network.viewmodel.EmployeeProfViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.bottom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_employee_prof.*
import net.simplifiedcoding.imageuploader.UploadRequestBody
import net.simplifiedcoding.imageuploader.getFileName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class EmployeeProfFragment : Fragment(), LifecycleObserver, UploadRequestBody.UploadCallback {
    private val employeeProfViewModel by viewModel<EmployeeProfViewModel>()
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    private var selectedImageUri: Uri? = null
    var epf_table_id = String()
    var socso_table_id = String()
    var pcb_table_id = String()
    var esi_id = String()
    var userid = String()
    var imageType = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_prof, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*   image_id.setOnClickListener {

           }*/
        PreferenceManager(requireContext()).apply {

            userid = getUserId()

        }
        epf_table.setOnClickListener {
            showTableList("epf_table")
        }

        et_socso_table.setOnClickListener {
            showTableList("socso_table")
        }

        et_pcb_table.setOnClickListener {
            showTableList("pcb_table")
        }
        et_eis_table.setOnClickListener {
            showTableList("eis_table")
        }

        et_employee_image.setOnClickListener {
            imageType = "employee_image"
            onImagePicker()
        }

        ed_passport_image.setOnClickListener {
            imageType = "passport_image"
            onImagePicker()
        }
        emp_submit.setOnClickListener {
            updateProff()
        }
        menu_back_proof.setOnClickListener {
            (activity as SlideMenu?)!!.openCloseNavigationDrawer()
        }
        backtoemp.setOnClickListener {
            findNavController().navigate(R.id.addEmployeeDetailsFragment2)
        }
    }

    private fun updateProff() {

        employeeProfViewModel.updateProof(RequestEmployeeProfSubmition(


            token = SessionSave.getSession("user_login_token", requireContext()),
            empid = SessionSave.getSession("emp_id", requireContext()),
            epfno = et_epfno.text.toString(),
            epf_table = epf_table.text.toString(),
            socso_icno = et_socso_icno.text.toString(),
            socso_table = et_socso_table.text.toString(),
            eis_table = et_eis_table.text.toString(),
            incometax_no = et_incometax_no.text.toString(),
            pcb_table = et_pcb_table.text.toString(),
            bank_name = ed_bank_name.text.toString(),
            bank_branch = ed_bank_branch.text.toString(),
            bank_acno = ed_bank_acno.text.toString(),
            photo = et_employee_image.text.toString(),
            ic_passport = ed_passport_image.text.toString()

        ), {
            Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
        }, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }


    private fun showTableList(type: String) {


        employeeProfViewModel.reqTable(RequestTables(
            token = SessionSave.getSession("user_login_token", requireContext()),
            type_name = type
        ), {
            showTableList(requireContext(), it, type)
        }, {

        })

        //

    }


    private fun showTableList(
        context: Context,
        category: ResponseTable,
        type: String

    ) {
        val view = layoutInflater.inflate(R.layout.bottom_dialog, null)
        val dialog = BottomSheetDialog(context)
        dialog.setContentView(view)
        println("Out_put_value" + " " + category.res)



        view.emp_type_list.adapter = TableDetailsAdapter { _category ->
            println("Hi_meaning_in_tamil" + " " + "sadasdasdaDD")

            if (type.equals("epf_table")) {
                epf_table.setText(_category.type_value)
                epf_table_id = _category.id.toString()
            } else if (type.equals("socso_table")) {
                et_socso_table.setText(_category.type_value)
                socso_table_id = _category.id.toString()
            } else if (type.equals("eis_table")) {
                et_eis_table.setText(_category.type_value)
                esi_id = _category.id.toString()
            } else if (type.equals("pcb_table")) {
                et_pcb_table.setText(_category.type_value)
                pcb_table_id = _category.id.toString()
            }
            dialog.dismiss()
        }

        (view.emp_type_list.adapter as TableDetailsAdapter).addCategoryList(category.res)



        dialog.show()
    }

    private fun requestRuntimePermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            ), READ_EXTERNAL_STORAGE_REQUEST_CODE
        )
    }

    private fun onImagePicker() {
        if (checkRuntimePermission()) {
            this.showDialogToPick()
        } else {
            requestRuntimePermission()
        }
    }

    private fun checkRuntimePermission(): Boolean {
        val writeablePermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        if (writeablePermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                val granted = grantResults.isNotEmpty()
                        && permissions.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && !ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    permissions[0]
                )
                when (granted) {
                    true -> this.showDialogToPick()  //  openImagePicker()
                    else -> Toast.makeText(requireContext(), "Check permission", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ImageConstants.GALLERY -> {
                    val selectedImage = data.data
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(
                            context?.contentResolver!!,
                            selectedImage
                        )
                    /*    ivProfile.loadCircularImage(bitmap)
                    mBitmap = bitmap*/
                    val result = getRealPathFromURI(data.data?.toString())
                    val file = File(result)

                    selectedImageUri = data?.data
                    uploadImage()

                }
                ImageConstants.CAMERA -> {
                    val bitmap = data.extras?.get("data") as Bitmap
                    selectedImageUri = getImageUriFromBitmap(requireContext(), bitmap)
                    uploadImage()
                    /*     val imagePath = context?.savePic(bitmap)
                    ivProfile.loadCircularImage(bitmap)
                    mBitmap = bitmap*/
                }
            }
        }
    }

    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }


    private fun getRealPathFromURI(contentURI: String?): String? {
        val contentUri = Uri.parse(contentURI)
        val cursor: Cursor? =
            activity?.contentResolver?.query(contentUri, null, null, null, null)
        return if (cursor == null) contentUri.path else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }

    private fun uploadImage() {


        val parcelFileDescriptor =
            requireActivity().contentResolver.openFileDescriptor(selectedImageUri!!, "r", null)
                ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(
            requireActivity().cacheDir,
            requireActivity().contentResolver.getFileName(selectedImageUri!!)
        )
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)


        val body = UploadRequestBody(file, "image", this)
        MyAPI().uploadImage(
            MultipartBody.Part.createFormData(
                "photo",
                file.name,
                body
            ),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {


                    if (imageType.equals("employee_image")) {
                        et_employee_image.setText(it.url)
                    } else {
                        ed_passport_image.setText(it.url)
                    }
                }
            }
        })

    }

    override fun onProgressUpdate(percentage: Int) {
        println("hi")
    }


}



