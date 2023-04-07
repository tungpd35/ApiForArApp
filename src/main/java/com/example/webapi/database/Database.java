package com.example.webapi.database;

import com.example.webapi.models.Clothes;
import com.example.webapi.models.Style;
import com.example.webapi.repositories.ClothesRepository;
import com.example.webapi.repositories.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(StyleRepository styleRepository, ClothesRepository clothesRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Style style1 = new Style("Áo Dài", "Áo dài là trang phục được cách tân theo hướng Tây hóa từ Áo ngũ thân lập lĩnh. Chính vì thế, áo dài còn gọi là áo tân thời (sau này còn được chiết eo). Áo dài được xem là trang phục truyền thống đồng thời cũng là quốc phục của nước ta. Áo dài thể hiện được bản sắc văn hóa dân tộc của người Việt Nam, được nhiều đại diện sắc đẹp của Việt Nam lựa chọn là trang phục trong nhất nhiều chương trình, lễ hội lớn nhằm tôn vinh giá trị áo dài cũng như giới thiệu quảng bá văn hóa, đất nước con người Việt Nam.","");
                Style style2 = new Style("Áo bà ba", "Áo bà ba là một loại trang phục phổ biến ở các miền quê miền Nam Việt Nam. Áo bà ba còn có tên gọi khác là áo Cánh.","");
                Style style3 = new Style("Áo chàm", "Áo chàm là một loại trang phục truyền thống của dân tộc Tày, Nùng, Thái và nhiều dân tộc khác trên vùng núi cao phía Bắc Việt Nam.\n","");
                Style style4 = new Style("Trang phục dân tộc Ê Đê", "Theo truyền thống, trang phục của người Ê Đê thường là màu đen hoặc chàm, trên đó có trang trí hoa văn sặc sỡ. Phần lớn phụ nữ đều mặc váy, quấn váy, còn đàn ông mặc khố, mặc áo. ","");
                Clothes clothes11 = new Clothes("Áo dài tứ thân truyền thống", "Áo dài từ cổ buông xuống dưới đầu gối chừng 20 cm. Áo có hai vạt trước và sau. Vạt trước có hai tà tách riêng nhau theo chiều dài. Vạt phía sau cũng được chia làm hai, nhưng khâu vào với nhau hình thành một đường dài gọi là sống áo. \n" +
                        "Áo tứ thân gồm hai vạt, bốn tà. Áo tứ thân không có khuy, dài và có hai tay áo để xỏ vào khi mặc. \n" +
                        "Bên trong sẽ mặc áo yếm để phối cùng áo tứ thân. Có thể là yếm cổ xây hoặc yếm cánh nhạn xẻ sâu xuống mãi tận dưới. \n" +
                        "Bên ngoài chiếc áo yếm sẽ có một chiếc áo cánh mỏng màu trắng. Dây thắt lưng thường có màu xanh.\n" +
                        "Phần lưng áo gồm hai mảnh vải ghép lại, thường là màu nâu hoặc nâu non ghép với màu cùng gam; phía trước có hai thân tách rời, được buộc lại với nhau, thả trước bụng, phía trên không gài khít mà để lộ yếm màu bên trong; cổ áo viền 1 – 2 cm. Áo tứ thân dài gần chấm gót, tay áo bó chặt.\n" +
                        "Loại áo này thường may màu tối, được xem là chiếc áo mộc mạc, khiêm tốn mang ý nghĩa tượng trưng cho 4 bậc sinh thành của hai vợ chồng.\n","121231");
                Clothes clothes12 = new Clothes("Áo tứ thân cách tân", "Những chiếc áo cách tân không cần mặc áo yếm bên trong nên thường sẽ được may cổ áo cao, quần may dài và đứng dáng để khi mặc thoải mái vận động hay di chuyển.\n","");
                Clothes clothes13 = new Clothes("Áo ngũ thân nữ", "nhiều màu sắc", "");
                Clothes clothes14 = new Clothes("Áo ngũ thân nam", "","");
                Clothes clothes15 = new Clothes("Áo dài trắng vải lụa", "","");
                Clothes clothes16 = new Clothes("Áo dài trắng xanh", "","");
                Clothes clothes17 = new Clothes("Áo dài tay lỡ:","","");
                Clothes clothes21 = new Clothes("Áo bà ba nữ",  "","");
                Clothes clothes22 = new Clothes("Áo bà ba nam","","");
                Clothes clothes31 = new Clothes("Áo chàm nam", "","");
                Clothes clothes32 = new Clothes("Áo chàm nữ", "","");
                Clothes clothes41 = new Clothes("Trang phục phụ nữ Ê đê", "","");
                Clothes clothes42 = new Clothes("Trang phục của đàn ông Ê Đê","o của đàn ông có thiết kế rộng và dài hơn của phụ nữ. Phần cổ áo được khoét tròn có xu hướng nghiêng về phía trước và được xẻ thành một đường ở trước ngực. Phần tay áo dài, vạt áo sau dài hơn vạt trước. Trên nền màu sẫm của thân, ống tay áo, viền cổ, nơi xẻ tà gấu áo được trang trí vài viền đỏ, trắng. Đặc biệt, khu vực giữa áo có mảng kẻ ngang trong bố cục hình chữ nhật. Đây là loại áo khá tiêu biểu của nam giới người Ê Đê. Ngoài ra còn có loại áo cộc tay đến khuỷu hoặc không có tay.\n" +
                        "Đàn ông Ê Đê dùng khố để che chắn nửa thân dưới của mình. Thông thường khố có chiều rộng rơi vào khoảng 30cm. Tùy vào dịp lễ mà khố có độ dài ngắn khác nhau. Khố được mặc bằng cách quấn vòng quanh eo, sau đó quấn tiếp qua háng. Một đầu khổ được giắt ở bên sườn giúp cố định chiếc khố được chắc chắn, phần còn lại được buông thả ở chính giữa phía trước. Các loại khố đẹp nhất là ktêh, drai, đrêch, piêk, còn các loại bong, băl là khố thường.\n","");

                Set<Clothes> clothesSet1 = new HashSet<>();
                clothesSet1.add(clothes11);
                clothesSet1.add(clothes12);
                clothesSet1.add(clothes13);
                clothesSet1.add(clothes14);
                clothesSet1.add(clothes15);
                clothesSet1.add(clothes16);
                clothesSet1.add(clothes17);
                style1.setClothes(clothesSet1);
                clothes11.setStyle(style1);
                clothes12.setStyle(style1);
                clothes13.setStyle(style1);
                clothes14.setStyle(style1);
                clothes15.setStyle(style1);
                clothes16.setStyle(style1);
                clothes17.setStyle(style1);
                styleRepository.save(style1);
                Set<Clothes> clothesSet2 = new HashSet<>();
                clothesSet2.add(clothes21);
                clothesSet2.add(clothes22);
                style2.setClothes(clothesSet2);
                clothes21.setStyle(style2);
                clothes22.setStyle(style2);
                Set<Clothes> clothesSet3 = new HashSet<>();
                clothesSet3.add(clothes31);
                clothesSet3.add(clothes32);
                style3.setClothes(clothesSet3);
                clothes31.setStyle(style3);
                clothes32.setStyle(style3);
                Set<Clothes> clothesSet4 = new HashSet<>();
                clothesSet4.add(clothes41);
                clothesSet4.add(clothes42);
                style4.setClothes(clothesSet4);
                clothes41.setStyle(style4);
                clothes42.setStyle(style4);
                styleRepository.save(style2);
                styleRepository.save(style3);
                styleRepository.save(style4);
            }
        };
    }
}
