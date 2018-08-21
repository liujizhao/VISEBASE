package application.base.com.base.model.main.home

import android.widget.ImageView
import application.base.com.base.BaseAppFragment
import application.base.com.base.R
import application.base.com.base.model.banner.CycleViewPagerFragment
import application.base.com.base.response.Banner
import application.base.com.base.response.Module
import application.base.com.base.utils.UIUtils
import com.androidlibrary.util.BBTools
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*

/**
 * Author Blank
 * Create on 2018/7/31 15:42
 * Description:
 */
class HomeFragment : BaseAppFragment(), HomeContract.IView {

    private var banners: List<Banner> = object : ArrayList<Banner>() {
        init {
            add(Banner(R.drawable.ic_launcher_background, 1))
            add(Banner(R.drawable.ic_launcher_background, 2))
        }
    }
    private var modules: List<Module> = object : ArrayList<Module>() {
        init {
            add(Module(R.drawable.splash_img, 1))
            add(Module(R.drawable.splash_img, 2))
        }
    }
    private var mBannerFragment: CycleViewPagerFragment? = null
    private var mHomePresenter: HomeContract.IPresenter? = null

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun initView() {
        if (mBannerFragment == null) {
            mBannerFragment = fragmentManager!!.findFragmentById(R.id.bannerFragment) as CycleViewPagerFragment
        }
        initBanner(banners)
        initModule(modules)
    }

    override fun initData() {
        mHomePresenter!!.banner(baseActivity)
        mHomePresenter!!.module(baseActivity)
    }

    private fun initBanner(banners: List<Banner>) {
        if (banners.isNotEmpty()) {
            val bannerViews = ArrayList<ImageView>()
            bannerViews.add(createImageView(banners[banners.size - 1]))
            for (banner in banners) {
                bannerViews.add(createImageView(banner))
            }
            bannerViews.add(createImageView(banners[0]))
            mBannerFragment!!.isCycle = true
            mBannerFragment!!.isWheel = true
            mBannerFragment!!.setData(bannerViews, banners,null)
            mBannerFragment!!.setTime(2000)
        } else {
            mBannerFragment!!.hide()
        }
    }

    private fun initModule(modules: List<Module>) {
        for (module in modules) {
            val imageView = ImageView(baseActivity)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageResource(module.drawableId)
            imageView.id = module.type
            BBTools.formatEveryOneLayoutParam(imageView, module.width, module.height, modules.size)
            imageView.setOnClickListener { v -> UIUtils.toast(baseActivity!!, v.id.toString()) }
            mModuleLayout.addView(imageView)
        }
    }

    override fun setPresenter(presenter: HomeContract.IPresenter) {
        mHomePresenter = presenter
    }

    private fun createImageView(banner: Banner): ImageView {
        val imageView = ImageView(activity)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setImageResource(banner.drawableId)
        return imageView
    }

    override fun bannerSuccess(banners: List<Banner>) {
        initBanner(banners)
    }

    override fun moduleSuccess(modules: List<Module>) {
        initModule(modules)
    }
}
