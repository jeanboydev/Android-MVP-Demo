
item ripple 效果：
    有界：android:background="?android:attr/selectableItemBackground"
    无界：android:background="?android:attr/selectableItemBackgroundBorderless"
    
button ripple 效果：
背景颜色 colorButtonNormal      文字颜色（默认：黑）          style="@style/Widget.AppCompat.Button"
背景颜色 colorAccent            文字颜色（默认：白）          style="@style/Widget.AppCompat.Button.Colored"
背景颜色 transparent            文字颜色（默认：黑）          style="@style/Widget.AppCompat.Button.Borderless"
背景颜色 transparent            文字颜色（默认：colorAccent） style="@style/Widget.AppCompat.Button.Borderless.Colored"
背景颜色 colorButtonNormal      文字颜色（默认：黑）          style="@style/Widget.AppCompat.Button.Small"

全局Button设置
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
    <!-- 设置button的Style -->
    <item name="buttonStyle">@style/Widget.AppCompat.Button</item>
    <item name="colorButtonNormal">@color/colorBlue</item>
</style>


修改Button圆角大小：在dimes.xml文件中复写abc_control_corner_material的值
<dimen name="abc_control_corner_material" tools:override="true">8dp</dimen>

AppBarLayout 取消投影
    app:elevation="0dp"