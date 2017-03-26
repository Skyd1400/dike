package tech.skydev.dike.ui.section

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.skydev.dike.R
import tech.skydev.dike.data.model.Titre
import tech.skydev.dike.util.ConversionUtil
import tech.skydev.dike.widget.GridSpacingItemDecoration

/**
 * A placeholder fragment containing a simple view.
 */
class SectionFragment : Fragment(), SectionContract.View {


    var mAdapter: SectionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = SectionAdapter(ArrayList(0))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        val recycler: RecyclerView = rootView?.findViewById(R.id.section_list) as RecyclerView
        val layoutManager: GridLayoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup= object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val size = if (position == 0) {
                    2
                } else {
                    1
                }
                return size
            }

        }
        layoutManager.orientation = GridLayoutManager.VERTICAL
        recycler.layoutManager =layoutManager
        recycler.adapter = mAdapter

        recycler.addItemDecoration(GridSpacingItemDecoration(ConversionUtil.dpToPx(context, 8), true))
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter!!.start()
    }



    var mPresenter: SectionContract.Presenter? = null


    override fun setPresenter(presenter: SectionContract.Presenter) {
        this.mPresenter = presenter;
    }

    override fun showSections(titres: ArrayList<Titre>) {
        mAdapter?.replaceItems(titres)
    }

    companion object {
        fun newInstance(): SectionFragment {
            return SectionFragment()
        }
    }
}
