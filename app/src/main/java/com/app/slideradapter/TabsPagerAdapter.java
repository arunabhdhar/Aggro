package com.app.slideradapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.aggro.R;


public class TabsPagerAdapter extends PagerAdapter {

	String tabs[]={"sds","Categosdry","Favourite"};
	Activity activity;
	public TabsPagerAdapter(Activity activity){
		this.activity=activity;
	}
    @Override
    public int getCount() {
        return tabs.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        Log.e("sdfdef", "" + position);
    	View view=null;
    	if(position%2==0){
          view = activity.getLayoutInflater().inflate(R.layout.pager_item,container, false);
    	}else{
    	  view = activity.getLayoutInflater().inflate(R.layout.pager_item_2,container, false);
    	}
        // Add the newly created View to the ViewPager
        container.addView(view);

        // Retrieve a TextView from the inflated View, and update it's text
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText(tabs[position]);
        Log.e("dvd", "" + title.getText());
        // Return the View
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}