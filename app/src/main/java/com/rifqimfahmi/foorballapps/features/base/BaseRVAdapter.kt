package com.rifqimfahmi.foorballapps.features.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rifqimfahmi.foorballapps.R
import com.rifqimfahmi.foorballapps.vo.Resource
import com.rifqimfahmi.foorballapps.vo.Status

/*
 * Created by Rifqi Mulya Fahmi on 03/12/18.
 */

abstract class BaseRVAdapter<T>(val ctx: Context?, var resource: Resource<List<T>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected abstract fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    open var errorMessage = "Failed to load data"

    open fun createLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadingItem(LayoutInflater.from(parent.context).inflate(R.layout.state_loading_list, parent, false))
    }

    open fun createErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ErrorItem(LayoutInflater.from(parent.context).inflate(R.layout.state_error_list, parent, false))
    }

    open fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptyItem(LayoutInflater.from(parent.context).inflate(R.layout.state_empty_list, parent, false))
    }


    fun submitData(data: Resource<List<T>>?) {
        data?.let {
            resource = it
            notifyDataSetChanged()
            if (data.status == Status.ERROR) {
                Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_DATA -> createDataViewHolder(parent)
            TYPE_LOADING -> createLoadingViewHolder(parent)
            TYPE_ERROR -> createErrorViewHolder(parent)
            TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> error("Unknown viewType: $viewType")
        }
    }

    override fun getItemCount(): Int {
        when (resource.status) {
            Status.LOADING -> return 1
            Status.ERROR -> {
                if (resource.data.isNullOrEmpty()) {
                    return 1
                }
            }
            Status.SUCCESS -> {
                if (resource.data.isNullOrEmpty()) {
                    return 1
                }
                return resource.data?.size ?: 0
            }
            Status.EMPTY -> {
                if (resource.data.isNullOrEmpty()) {
                    return 1
                }
            }
        }
        return resource.data?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        when (resource.status) {
            Status.LOADING -> {
                return TYPE_LOADING
            }
            Status.ERROR -> {
                if (resource.data.isNullOrEmpty()) {
                    return TYPE_ERROR
                }
            }
            Status.SUCCESS -> {
                if (resource.data.isNullOrEmpty()) {
                    return TYPE_EMPTY
                }
                return TYPE_DATA
            }
            Status.EMPTY -> {
                if (resource.data.isNullOrEmpty()) {
                    return TYPE_EMPTY
                }
            }
        }
        return TYPE_DATA
    }

    companion object {
        const val TYPE_DATA = 0
        const val TYPE_ERROR = 1
        const val TYPE_LOADING = 2
        const val TYPE_EMPTY = 3
    }

    inner class LoadingItem(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ErrorItem(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class EmptyItem(itemView: View) : RecyclerView.ViewHolder(itemView)
}