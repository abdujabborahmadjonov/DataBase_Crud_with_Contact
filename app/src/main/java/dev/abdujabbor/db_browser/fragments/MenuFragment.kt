package dev.abdujabbor.db_browser.fragments

    import android.annotation.SuppressLint
    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
    import androidx.core.os.bundleOf
    import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
    import androidx.recyclerview.widget.ItemTouchHelper
    import androidx.recyclerview.widget.RecyclerView
    import dev.abdujabbor.db_browser.MyData
    import dev.abdujabbor.db_browser.R
import dev.abdujabbor.db_browser.adapters.MyRvAdapter
import dev.abdujabbor.db_browser.adapters.RvAction
import dev.abdujabbor.db_browser.databinding.FragmentMenuBinding
import dev.abdujabbor.db_browser.db.My_Db_Browser
import dev.abdujabbor.db_browser.model.Uset

class MenuFragment : Fragment(), RvAction {
    lateinit var myRvAdapter:MyRvAdapter
    val binding by lazy { FragmentMenuBinding.inflate(layoutInflater) }
    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val myDbBrowser =My_Db_Browser(binding.root.context)
        myRvAdapter = MyRvAdapter(myDbBrowser.getAllContacts(),this)
        binding.myRecycleView.adapter =myRvAdapter
        return binding.root
    }
   //show menu add
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top_plus, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.addFragment)
        MyData.edtoradd = 0
        return super.onOptionsItemSelected(item)
    }

    //show pop menu
    override fun showPopupMenu(view: View,position:Int, user: Uset) {
        val popupMenu = PopupMenu(binding.root.context,view)
        popupMenu.inflate(R.menu.popupmenu_menu)
        popupMenu.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.delete->{
                    myRvAdapter.notifyItemRemoved(position)
                    My_Db_Browser(binding.root.context).deleteUser(user)
                    myRvAdapter.list.remove(user)
                }
                R.id.update->{
                    MyData.edtoradd = 1
                    findNavController().navigate(R.id.addFragment, bundleOf("list" to user))
                }
            }

            true
        }
        popupMenu.show()
    }

    override fun click(position: Int, user: Uset) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+998${user.number}")
        startActivity(intent)

    }

    override fun clicksms(position: Int, user: Uset) {
         val smsUri = Uri.parse("smsto:+998${user.number}")
        val smsIntent = Intent(Intent.ACTION_SENDTO,smsUri)
        startActivity(smsIntent)
    }
}