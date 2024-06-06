package gob.pe.msi.trakingrealtime.presentation.common.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import gob.pe.msi.trakingrealtime.R
import gob.pe.msi.trakingrealtime.databinding.ViewWidgetItemBigBinding

class CustomItemBig @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ViewWidgetItemBigBinding =
        ViewWidgetItemBigBinding.inflate(LayoutInflater.from(context), this, true)
    //private var binding: ViewWidgetItemBigBinding = ViewWidgetItemBigBinding.inflate(LayoutInflater.from(context), this, true)

    var title: String? = null
    var itemText: String? = null
    var subItemText: String? = null
    var enabledButton: Boolean = true
    var itemIcon: Int = 0
    var isDetail: Boolean = false
    var buttonGo: TextView? = null
    var listener: ItemListener? = null
    var tag: String? = null

    init {
        attrs?.let { setupAttrs(context, it) }
        binding.event = this
        setupUI()
    }

    private fun setupAttrs(context: Context, attrs: AttributeSet) {
        val type = context.obtainStyledAttributes(attrs, R.styleable.CustomItemBig, 0, 0)

        try {
            tag = type.getString(R.styleable.CustomItemBig_tagItem)
            enabledButton = type.getBoolean(R.styleable.CustomItemBig_enableButton, true)
            isDetail = type.getBoolean(R.styleable.CustomItemBig_isDetail, false)
            itemIcon = type.getResourceId(
                R.styleable.CustomItemBig_iconDown,
                R.drawable.product_placeholder
            )
            title = type.getString(R.styleable.CustomItemBig_itemTitle)
            itemText = type.getString(R.styleable.CustomItemBig_itemText)
            subItemText = type.getString(R.styleable.CustomItemBig_subItemText)

            binding.txtGo.setOnClickListener {
                listener?.testClick(tag)
            }
        } finally {
            type.recycle()
        }
    }

    private fun setupUI() {
        setup()
    }

    fun setTextItem(text: String?) {
        binding.txtItem.text = text
    }

    fun setTextSubItem(text: String?) {
        binding.txtSubItem.text = text
    }

    fun setVisibleDetail(value: Boolean) {
        binding.clBody.visibility = if (value) View.VISIBLE else View.GONE
    }

    fun setTextButtonAdd(text: String?) {
        binding.txtGo.text = text
    }

    fun setOnclickItem(listener: ItemListener) {
        this.listener = listener
    }

    fun setTextItemColor(id: Int) {
        binding.txtItem.setTextColor(id)
    }

    fun setTextSubItemColor(id: Int) {
        binding.txtSubItem.setTextColor(id)
    }

    fun setDrawableResource(drawable: Drawable?) {
        binding.imgItem.setImageDrawable(drawable)
    }

    fun setIcon(url: String) {

        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.product_placeholder)
            .error(R.drawable.product_placeholder)
            .into(binding.imgItem)
    }

    private fun setup() {
        buttonGo = binding.txtGo
        binding.txtTitle.text = title
        binding.txtItem.text = itemText
        binding.txtSubItem.text = subItemText
        enabledComponentes(enabledButton)
        setVisibleDetail(isDetail)
        tag = tag ?: ""
    }

    fun enabledComponentes(value: Boolean) {
        binding.flItem.isEnabled = value
        enabledButton = value
        setAlpha()
    }

    private fun setAlpha() {
        val alphaValue = if (enabledButton) 1f else 0.3f
        binding.txtTitle.alpha = alphaValue
        binding.txtGo.alpha = alphaValue
    }

    interface ItemListener {
        fun testClick(tag: String?)
    }
}