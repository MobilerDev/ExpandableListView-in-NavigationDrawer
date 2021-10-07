package com.example.expandablelistviewnavigationdrawer

import android.os.Bundle
import android.view.Menu
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.expandablelistviewnavigationdrawer.databinding.ActivityMainBinding
import com.example.expandablelistviewnavigationdrawer.ui.expanded.ExpandedMenuAdapter
import com.example.expandablelistviewnavigationdrawer.ui.expanded.ExpandedMenuModel
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val headerList: ArrayList<ExpandedMenuModel> = ArrayList<ExpandedMenuModel>()
    private val childList: HashMap<ExpandedMenuModel, ArrayList<String>> =
        HashMap<ExpandedMenuModel, ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Add content in drawer
        prepareListData()

        //Initialize and Assign ExpandableListView
        val expandableListView: ExpandableListView = binding.expandedListView


        //Set Adapter in ExpandableListView :
        val mMenuAdapter =
            ExpandedMenuAdapter(this, headerList, childList, expandableListView)
        expandableListView.setAdapter(mMenuAdapter)

        expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            when (groupPosition) {
                // Position, first title = 0, second title = 1, .......

                0 -> { //Example - How to expand the drawer list
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.setOnChildClickListener { parent, _, groupPosition, childPosition, _ ->
                            when (childPosition) {
                                0 -> {
                                    // Do anything you wanted

                                }
                                1 -> {
                                    // Do anything you wanted

                                }
                            }
                            // Collapse the expanded list
                            parent.collapseGroup(groupPosition)
                        }
                    }
                }
                1 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.setOnChildClickListener { parent, _, groupPosition, childPosition, _ ->
                            when (childPosition) {
                                0 -> {
                                    // Do anything you wanted


                                }
                                1 -> {
                                    // Do anything you wanted


                                }
                                2 -> {
                                    // Do anything you wanted


                                }
                            }
                            parent.collapseGroup(groupPosition)
                        }
                    }
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun prepareListData() {

        val headerFirst = ExpandedMenuModel()
        headerFirst.iconName = ("Header1")
        headerFirst.iconImg = R.drawable.ic_menu_camera

        headerList.add(headerFirst)

        val headerSecond = ExpandedMenuModel()
        headerSecond.iconName = ("Header2")
        headerSecond.iconImg = R.drawable.ic_menu_camera

        headerList.add(headerSecond)

        // Second,  Adding child data (Subtitle)
        val childFirst = ArrayList<String>()
        childFirst.add("Child1")
        childFirst.add("Child2")

        val childSecond = ArrayList<String>()
        childSecond.add("Child1")
        childSecond.add("Child2")
        childSecond.add("Child3")

        // This case, only 2 of Big title have sub title
        childList[headerList[0]] = childFirst
        childList[headerList[1]] = childSecond
    }
}