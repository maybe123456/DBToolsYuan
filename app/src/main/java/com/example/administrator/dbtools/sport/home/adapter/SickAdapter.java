//package com.example.administrator.dbtools.sport.home.adapter;
//
//
//import android.content.Context;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.administrator.dbtools.R;
//import com.example.administrator.dbtools.base.adapter.BasicAdapter;
//import com.example.administrator.dbtools.sport.home.bean.SickMainBean;
//
//
//public class SickAdapter extends BasicAdapter<SickMainBean,SickAdapter.Viewhodler> {
//
//	// 回调监听
//	private CallBackListener mCallBackListener;
//
//	public SickAdapter(Context context) {
//		super(context);
//	}
//
//	@Override
//	protected int getItemViewLayoutId() {
//		return R.layout.sick_fragment_item;
//	}
//
//	@Override
//	protected void bindView(int position, final SickMainBean object, Viewhodler viewhodler) {
//
//		viewhodler.tetle.setText(object.getTitle());
//		if(object.getTitle().contains("赛程")){
//			viewhodler.imageView.setImageResource(R.mipmap.match);
//		}else if(object.getTitle().contains("积分")){
//			viewhodler.imageView.setImageResource(R.mipmap.score);
//		}else if(object.getTitle().contains("助攻")){
//			viewhodler.imageView.setImageResource(R.mipmap.assisting);
//		}else{
//			viewhodler.imageView.setImageResource(R.mipmap.shooter);
//		}
//
////		viewhodler.image.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				Intent goodsIntent = new Intent(context, SearchActivity.class);
////				goodsIntent.putExtra("typeId",object.getTypeId());
////				goodsIntent.putExtra("typeName",object.getTypeName());
////				context.startActivity(goodsIntent);
////			}
////		});
//
//	}
//
//	@Override
//	protected Viewhodler loadHolder(View v) {
//		return new Viewhodler(v);
//	}
//
//	class Viewhodler {
//
////		public Viewhodler(View v) {
////			IOCUtils.inject(this, v);
////		}
//
//		/**商品名称*/
////		@ViewInject(R.id.title)
//		private TextView title;
//		v
//
//	}
//
//	/**
//	 * 设置回调监听
//	 * @author leibing
//	 * @createTime 2016/09/28
//	 * @lastModify 2016/09/28
//	 * @param mCallBackListener 回调监听
//	 * @return
//	 */
//	public void setCallBackListener(CallBackListener mCallBackListener){
//		this.mCallBackListener = mCallBackListener;
//	}
//
//	/**
//	 * @interfaceName: CallBackListener
//	 * @interfaceDescription: 回调监听
//	 * @author: leibing
//	 * @createTime: 2016/09/28
//	 */
//	public interface CallBackListener{
//		void callBackImg(ImageView goodsImg);
//	}
//}
//
