package com.hr

import PreferenceManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.hr.data.network.api.request.RequestUserLogin
import com.hr.data.network.viewmodel.UserLoginViewModel
import com.hr.menu.SlideMenu
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), LifecycleObserver {
private val userLoginViewModel by viewModel<UserLoginViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           /* val c: Calendar = Calendar.getInstance()
            val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)

            if (timeOfDay >= 0 && timeOfDay < 12) {
                Toast.makeText(requireContext(), "Good Morning", Toast.LENGTH_SHORT).show()
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                Toast.makeText(requireContext(), "Good Afternoon", Toast.LENGTH_SHORT).show()
            } else if (timeOfDay >= 16 && timeOfDay < 21) {
                Toast.makeText(requireContext(), "Good Evening", Toast.LENGTH_SHORT).show()
            } else if (timeOfDay >= 21 && timeOfDay < 24) {
                Toast.makeText(requireContext(), "Good Night", Toast.LENGTH_SHORT).show()
            }*/

            val userId =  SessionSave.getSession("user_login_token", requireContext())

            if(!userId.equals(""))
            {
                val moveNext = Intent(requireContext(), SlideMenu::class.java)
                startActivity(moveNext)
                requireActivity().finish()
            }

        btnContinue.setOnClickListener {
            loading_spinner.visibility = View.VISIBLE

            if (etName.text.toString().trim().equals(""))
            {
                Toast.makeText(requireContext(),"Enter you username ",Toast.LENGTH_LONG).show()
            }
            else if (etpass.text.toString().trim().equals(""))
            {
                Toast.makeText(requireContext(),"Enter you password ",Toast.LENGTH_LONG).show()

            }
            else
            {
                getLoginRespnse()

            }
        }
    }

    private fun getLoginRespnse() {
        userLoginViewModel.reqUserLogin(RequestUserLogin(

            username = etName.text.toString().trim(),
            password = etpass.text.toString().trim()

        ), {
            loading_spinner.visibility = View.GONE
            if (it.token.equals("")) {
                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()

            } else {
                PreferenceManager(requireContext()).apply {

                    setUserId(it.token)
                    /* setVehicleName(it.name)
                    setMobileNumber(etName.text.toString().trim())*/
                }
                SessionSave.saveSession("user_login_token", it.token, requireContext())
                Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                val moveNext = Intent(requireContext(), SlideMenu::class.java)
                startActivity(moveNext)
            }

        }, {
            loading_spinner.visibility = View.GONE

            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }
}