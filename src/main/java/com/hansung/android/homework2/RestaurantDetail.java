package com.hansung.android.homework2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

//맛집을 클릭했을때 DestaurantDetail로 이동
public class RestaurantDetail extends AppCompatActivity {
    final static String TAG="SQLITEDBTEST";
    private Cursor cursor2;  //메뉴 데이터베이스 커서
    private Cursor cursor;   //맛집 데이터베이스 커서
    private DBHelper RestaurantDBHelper;
    private DBHelper2 MenuDBHelper;

    int resId;  //맛집을 식별하기위한 ID변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("한성 맛집 앱");   //어플 이름 설정
        setContentView(R.layout.restaurant_detail);          //xml에 화면

        RestaurantDBHelper = new DBHelper(this);
        MenuDBHelper = new DBHelper2(this);

        Intent intent = getIntent();  //RestaurantCatalog에서 받아온 맛집 요소 입력
        resId = intent.getIntExtra("dataFromRestaurantCatalog0",-1);
        Cursor cursor = RestaurantDBHelper.getAllUsersBySQL(resId);
        //맛집의 ID만을 받아와서 데이터베이스에 저장된 값을 넣어주는 작업
        //"Select * FROM " + RestaunrantContract.Users.Restaurant_TABLE_NAME + " WHERE " + RestaunrantContract.Users._ID + " = "+resId;

        cursor.moveToFirst();  //커서의 위치를 맨 앞으로 오게하기 위한 메소드

//        String msg1 = intent.getStringExtra("dataFromRestaurantCatalog1");
        TextView home = (TextView)findViewById(R.id.restaurant_detail_name);  //맛집주소 삽입
        home.setText(cursor.getString(2));

//        String msg2 = intent.getStringExtra("dataFromRestaurantCatalog3");
        TextView phone = (TextView)findViewById(R.id.restaurant_detail_phone);  //맛집번호 입력
        phone.setText(cursor.getString(3));

//        String msg3 = intent.getStringExtra("dataFromRestaurantCatalog4");
        TextView time = (TextView)findViewById(R.id.retaurant_detail_time);  //맛집시간 입력
        time.setText(cursor.getString(4));

//        String imageuri = intent.getStringExtra("dataFromRestaurantCatalog5");
        ImageView img = findViewById(R.id.restaurant_detail_image);  //맛집이미지 입력
        img.setImageURI(Uri.parse(cursor.getString(5)));

        viewAllToListView_MENU();  //입력된 메뉴를 불러오는 메소드

    }
    private void viewAllToListView_MENU() {  //입력된 메뉴를 불러오는 메소드
        cursor2 = MenuDBHelper.getAllUsersBySQL(resId);  //맛집ID에 맞게 메뉴커서를 이동시킨다.
       //"Select * FROM " + MenuContract.Menu.Menu_Table_Name +" WHERE "+ MenuContract.Menu.KEY_Res_Id +" = "+resId;
        SimpleCursorAdapter MenuAdapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.menu_item, cursor2, new String[]{
                MenuContract.Menu.KEY_MENU_Name,
                MenuContract.Menu.KEY_MENU_PRICE,
                MenuContract.Menu.KEY_Menu_Image,
        }, new int[]{R.id.menu_item_name, R.id.menu_item_price, R.id.menu_item_icon}, 0);
        //메뉴 이름, 메뉴 가격, 메뉴 이미지 불러와서 리스트뷰로 표현

        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(MenuAdapter);
        lv.setDivider(new ColorDrawable(Color.RED));
        lv.setDividerHeight(5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //옵션 앱바
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //옵션바 클릭했을 때
        switch (item.getItemId()) {
            case R.id.quick_action2:
                Intent intent = new Intent(RestaurantDetail.this, MenuRegister.class);
                intent.putExtra("dataFromRestaurantDetail", resId);
                //맛집의 ID인 resId를 항상 같이 넘겨준다.
                startActivity(intent);
               finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doOnBtnClick(View view) {   //전화 다이얼 버튼 및 인텐트 설정
        Intent implicit_intent = null;
        switch (view.getId()) {
            case R.id.buttonDialActivity:
                // 027604499 전화번호로 다이얼 작업을 수행할 수 있도록 인텐트 설정
                implicit_intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:027604499"));
                break;
        }
        if (implicit_intent != null)
            startActivity(implicit_intent);
    }
}