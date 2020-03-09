package com.jingchengsoft.ykplatform.common.copy.kotlin

import com.jingchengsoft.dzjplatform.R
import com.jingchengsoft.dzjplatform.common.MyFragment


/**
 * @author MaybeSix
 * @date 2019/11/6
 * @desc TODO.
 */
class CopyFragment : MyFragment<CopyActivity>() {

    companion object {

        fun newInstance(): CopyFragment {
            return CopyFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_copy
    }

    override fun initView() {

    }

    override fun initData() {

    }


}