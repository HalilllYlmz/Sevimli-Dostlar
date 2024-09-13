# Sevimli Dostlar Uygulaması

Bu proje, hayvanların İngilizce ve Türkçe isimlerini öğretmeye yönelik bir uygulamadır. **Sevimli Dostlar** uygulaması, kullanıcının hayvanları öğrenmesine ve ilerlemesini takip etmesine yardımcı olmak amacıyla geliştirilmiştir.

## Proje Açıklaması

Uygulama, kullanıcıya öğrenilmemiş hayvanlar ile öğrenilen hayvanları ayıran iki ana ekran sunar. Ayrıca her hayvanın detay sayfasında, hayvanın İngilizce ismi, Türkçe çevirisi ve İngilizce telaffuzunu dinleme imkanı sağlanır. Kullanıcı, hayvanları öğrenildikçe işaretleyebilir ve bu bilgiler kalıcı olarak kaydedilir.

### 1. **Fragment'lar:**
   - **HomeFragment (Henüz Öğrenilmemiş Hayvanlar):**
     - Henüz öğrenilmemiş hayvanların listelendiği ekran.
     - Kullanıcı bu ekrandan hayvanın detay sayfasına geçiş yapabilir.
   - **LearnedFragment (Öğrenilen Hayvanlar):**
     - Öğrenilen hayvanların gösterildiği ekran.
     - Kullanıcı, öğrenilen hayvanları burada görüntüleyebilir ve detay sayfasına ulaşabilir.
   - **DetailFragment (Hayvan Detay Sayfası):**
     - Hayvanın İngilizce ve Türkçe ismi ile İngilizce okunuşu yer alır.
     - İngilizce okunuşunu dinlemek için ses butonu bulunur.
     - Hayvan öğrenildiğinde kullanıcı "Learned" switch'ini aktif hale getirebilir, aksi durumda kapatabilir.
     - Veriler **SharedPreferences** ile kalıcı olarak saklanır.

### 2. **BottomNavigationView:**
   - Uygulamada iki sekme (tab) bulunur:
     1. **Home (Henüz Öğrenilmemiş Hayvanlar)**
     2. **Learned (Öğrenilen Hayvanlar)**

### 3. **Veri Kaydetme ve Yönetim:**
   - Kullanıcıların öğrenilen hayvanları kaydedebilmesi ve uygulamanın her açılışında bu verilerin korunması için **SharedPreferences** kullanılır.

### 4. **ViewBinding:**
   - UI bileşenleri ve veri bağlama işlemleri için **ViewBinding** kullanılmıştır.

## Kurulum

Bu projeyi kendi makinenizde çalıştırmak için aşağıdaki adımları izleyin:

1. **Proje Deposu:** Bu projeyi GitHub'dan klonlayın:
    ```bash
    git clone https://github.com/kullaniciadi/sevimli-dostlar.git
    ```
2. **Android Studio:** Projeyi Android Studio ile açın.
3. **Build:** Projeyi build edin ve çalıştırın.

## Kullanım

1. **Uygulama Açılışı:** Uygulama açıldığında, henüz öğrenilmemiş hayvanlar listelenecektir.
2. **Hayvan Seçimi:** Liste üzerinden bir hayvan seçerek detay sayfasına geçiş yapabilirsiniz.
3. **Learned İşareti:** Detay sayfasında, "Learned" switch'ini kullanarak hayvanı öğrendiklerinize ekleyebilir ya da çıkartabilirsiniz.
4. **Learned Ekranı:** "Learned" sekmesine geçerek öğrendiğiniz hayvanları görüntüleyebilirsiniz.

## Kullanılan Teknolojiler

- **Kotlin**
- **Jetpack Navigation**
- **RecyclerView**
- **SharedPreferences**
- **ViewBinding**
- **BottomNavigationView**

## Demo Video

[![Demo Video](https://img.youtube.com/vi/wd-VVGRSD_E/0.jpg)](https://www.youtube.com/shorts/wd-VVGRSD_E)

## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına göz atabilirsiniz.
