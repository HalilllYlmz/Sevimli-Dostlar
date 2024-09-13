# Sevimli Dostlar

Merhaba ve **Sevimli Dostlar** uygulamasına hoş geldiniz! Bu uygulama, hayvanların İngilizce isimlerini öğrenmenize yardımcı olur. Hayvanları öğrenildikçe işaretleyebilir ve ilerlemenizi takip edebilirsiniz. Uygulama, iki ana fragment ve bir detay sayfası ile basit bir yapıya sahiptir.

## Özellikler

1. **HomeFragment (Henüz Öğrenilmemiş Hayvanlar)**:
   - Henüz öğrenilmemiş hayvanlar burada listelenir.
   - Listeye tıklayarak hayvanların detay sayfasına gidilebilir.
   
2. **LearnedFragment (Öğrenilen Hayvanlar)**:
   - Öğrenilen hayvanlar burada listelenir.
   - Detay sayfasında hayvan bilgileri görüntülenebilir ve “Unlearn” butonu ile tekrar öğrenilmemiş hale getirilebilir.
   
3. **Detay Sayfası**:
   - Hayvanın İngilizce ve Türkçe isimlerini gösterir.
   - İngilizce telaffuzunu dinlemek için bir ses simgesi bulunur.
   - Kullanıcı hayvanı öğrenilmiş olarak işaretlemek için bir switch kullanabilir.
   - Bilgiler, `SharedPreferences` kullanılarak kaydedilir.

## Proje Gereksinimleri

- **RecyclerView**: Henüz öğrenilmemiş ve öğrenilen hayvanlar iki ayrı listede gösterilir.
- **ViewBinding**: UI bileşenleri için `ViewBinding` kullanılmıştır.
- **BottomNavigationView**: Uygulamada iki sekme bulunur:
  - **Home (Henüz Öğrenilmemiş Hayvanlar)**
  - **Learned (Öğrenilen Hayvanlar)**
- **Jetpack Navigation**: Sayfalar arası geçişler `Jetpack Navigation` kullanılarak yapılmaktadır.
- **SharedPreferences**: Kullanıcı öğrenilmiş hayvanları işaretleyebilir ve bu veriler kalıcı olarak kaydedilir.
- **Swipe to Refresh**: HomeFragment'teki listeyi rastgele yeniden sıralamak için “Swipe to Refresh” özelliği eklenmiştir.

## Kurulum

1. Bu projeyi klonlayın:
   ```bash
   git clone https://github.com/HalilllYlmz/Sevimli-Dostlar
