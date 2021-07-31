package com.example.contactslistview.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.contactslistview.model.ListAdapter
import com.example.contactslistview.R
import com.example.contactslistview.databinding.ActivityMainBinding
import com.example.contactslistview.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:UserViewModel
    private var userArrayList = ArrayList<String>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter :ListAdapter
    private lateinit var headerText : TextView
    private lateinit var footerText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider.AndroidViewModelFactory.
        getInstance(application).create(UserViewModel::class.java)
        userArrayList.clear()
        userArrayList.addAll(viewModel.returnUser().items)

        val toolbar = binding.toobar
        setSupportActionBar(toolbar)

        val header = layoutInflater.inflate(
            R.layout.header_list, binding.listView,
            false) as ViewGroup

        headerText = header.findViewById(R.id.textView)
        headerText.text = getString(R.string.total_users,userArrayList.size)

        val footer = layoutInflater.inflate(
            R.layout.footer_list, binding.listView,
            false) as ViewGroup

        footerText = footer.findViewById(R.id.textView)
        footerText.text = getString(R.string.total_users,userArrayList.size)

        binding.listView.addHeaderView(header)
        binding.listView.addFooterView(footer)
        binding.listView.isClickable = true
        adapter = ListAdapter(this, userArrayList)
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->

            val name = getUser()
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("name", name[position-1])
            startActivity(intent)
        }
    }

    private fun getUser():ArrayList<String>{
        return adapter.getUsers()!!
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemSearch = menu?.findItem(R.id.searchView)
        val searchView = itemSearch?.actionView as SearchView
        supportActionBar?.setDisplayShowTitleEnabled(false)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_users)
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->

        }
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText!!)
                var totalUsers = adapter.getUsers()?.size
                headerText.text = getString(R.string.total_users,totalUsers)
                footerText.text = getString(R.string.total_users,totalUsers)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}