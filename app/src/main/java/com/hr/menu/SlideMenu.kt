package com.hr.menu

import android.app.ActionBar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.hr.R
import com.hr.menu.hrui.proof.EmployeeProfFragment
import com.hr.utility.SessionSave
import kotlinx.android.synthetic.main.activity_slide_menu.*


class SlideMenu : AppCompatActivity() {

     lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var appBarConfigurationBottom: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)



        getSupportActionBar()!!.hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homePageFragment,
                R.id.nav_gallery,
                R.id.addNewEmployeePersonal2,
                R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setItemIconTintList(null);

        appBarConfigurationBottom = AppBarConfiguration(
            setOf(
                R.id.homePageFragment,
                R.id.claimFragment,
                R.id.attandanceFragment,
                R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfigurationBottom)
        bottomNavigation.setupWithNavController(navController)
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


     fun openCloseNavigationDrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        SessionSave.saveSession("emp_edit","No",this)

    }
}