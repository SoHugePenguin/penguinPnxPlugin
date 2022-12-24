package Minecraft.nukkit.cn.sohugepenguin.plugin.testPlugin.Windows.Shop;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class Shop extends FormWindowSimple {
    public Shop() {
        super("商店系统", "全球市场！\n在这里购买你想要的物品！  每日价格会有波动！");
    }

    public static FormWindowSimple getWindowShop() {
        FormWindowSimple home = new Shop();
        home.addButton(new ElementButton("货币转换", new ElementButtonImageData("path", "textures/ui/ball")));
        home.addButton(new ElementButton("全球市场", new ElementButtonImageData("path", "textures/ui/the_world")));
        home.addButton(new ElementButton("投资理财", new ElementButtonImageData("path", "textures/ui/sea_tortoise")));
        home.addButton(new ElementButton("账单流水", new ElementButtonImageData("path", "textures/ui/icon_book_writable")));
        home.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/back")));
        return home;
    }
}
