package application.base.com.base.model.main

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import application.base.com.base.AppBaseActivity
import application.base.com.base.LOGIN_RESULT
import application.base.com.base.MAIN_BUNDLE
import application.base.com.base.R
import application.base.com.base.dao.AppDatabase
import application.base.com.base.model.main.enquiry.EnquiryFragment
import application.base.com.base.model.main.enquiry.EnquiryPresenter
import application.base.com.base.model.main.home.HomeFragment
import application.base.com.base.model.main.home.HomePresenter
import application.base.com.base.model.main.me.MineFragment
import application.base.com.base.model.main.order.OrderFragment
import application.base.com.base.response.User
import application.base.com.base.utils.BottomNavigationViewHelper
import application.base.com.base.utils.UIUtils
import com.androidlibrary.BaseFragment
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

/**
 * Author Blank
 * Create on 2018/7/31 15:39
 * Description:
 */
class MainActivity : AppBaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var menuItem: MenuItem? = null

    private val mMainFragments = ArrayList<BaseFragment>()
    private var mUserInfo: User? = null
    private var mHomeFragment: HomeFragment? = null
    private var mEnquiryFragment: EnquiryFragment? = null
    private var mOrderFragment: OrderFragment? = null
    private var mMineFragment: MineFragment? = null

    override val layoutId: Int
        get() = R.layout.main_activity

    override fun initBefore() {
        super.initBefore()
        val bundleExtra = intent.getBundleExtra(MAIN_BUNDLE)
        mUserInfo = bundleExtra.getSerializable(LOGIN_RESULT) as User?
        if (mUserInfo != null) {
            //save database
            AppDatabase.getInstance(this).userDao().insertUser(mUserInfo)
        } else {
            //database userInfo
            mUserInfo = AppDatabase.getInstance(this).userDao().user
        }
    }

    override fun initData() {
        HomePresenter(mHomeFragment)
        EnquiryPresenter(mEnquiryFragment)
    }

    override fun initView() {

        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView!!)
        mBottomNavigationView!!.setOnNavigationItemSelectedListener(this)
        mHomeFragment = HomeFragment()
        mEnquiryFragment = EnquiryFragment()
        mOrderFragment = OrderFragment()
        mMineFragment = MineFragment()

        mMainFragments.add(mHomeFragment!!)
        mMainFragments.add(mEnquiryFragment!!)
        mMainFragments.add(mOrderFragment!!)
        mMainFragments.add(mMineFragment!!)

        mMainPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                var currentPosition = position
                if (menuItem != null) {
                    menuItem!!.isChecked = false
                } else {
                    mBottomNavigationView!!.menu.getItem(0).isChecked = false
                }
                if (currentPosition == mMainFragments.size - 1 || currentPosition == 2) {
                    currentPosition += 1
                }
                menuItem = mBottomNavigationView!!.menu.getItem(currentPosition)
                menuItem!!.isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mMainPager!!.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mMainFragments[position]
            }

            override fun getCount(): Int {
                return mMainFragments.size
            }
        }
        mMainPager!!.offscreenPageLimit = 4

        //禁止ViewPager滑动
        //        mMainPager.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                return true;
        //            }
        //        });
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> mMainPager!!.currentItem = 0
            R.id.enquiry -> mMainPager!!.currentItem = 1
            R.id.order -> mMainPager!!.currentItem = 2
            R.id.mine -> mMainPager!!.currentItem = 3
            R.id.center -> UIUtils.toast(this, "center")
        }
        return false
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }
}
