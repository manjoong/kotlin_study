package com.drj.a.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bm_result.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PhotoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_URL = "uri"

class PhotoFragment : Fragment() {

    private var uri: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            uri = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(uri).into(imageView)
    }




    companion object {

        @JvmStatic
        fun newInstance(uri: String) =
                PhotoFragment().apply{
                    arguments = Bundle().apply {
                        putString(ARG_URL, uri)
                    }
        }
    }
}// Required empty public constructor
