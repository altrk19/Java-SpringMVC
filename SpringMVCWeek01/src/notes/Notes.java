package notes;

public class Notes {

}

// MVC -> model view controller , architectural pattern dir.

// bu kavram 1970 lere dayanmaktadir. ( bu yillarda masaustu uygulamalari hedef almaktaydi)

// MVC patterninin temel prensibi ; seperation of concern (iliskilerin/etkilerin ayrilmasi)
// her bir component/modul/parca kendi sorumlulugunu ustlenmesi anlamian gelmektedir.
// boylelikle kodumuz  temiz mimari (clean architecture)
// surdurulebilir (maintanable) kod ozelligine kavusacaktir.

// #### Model
// POJO siniflar (plain old java object)
// model siniflari projeye ozgu siniflardir.
// Product.java , Customer.java , Account.java , User.java
// Book.java , Author.java , Publisher.java .. gibi siniflar kavramlar.

// #### View
// view -> presentation kismindan sorumludur. (datayi goster)

// View -> JSP , Facelets(xhtml) , XML , JSON , PDF , velocity , freemarker, thmyleaf ..

// #### Controller
// Istek geldiginde , istegi karsilayan component tir.

// MVC ;
// seperation of concern saglar.
// louse coupling
// - durum olarak complexity bir miktar artar.


// MVC nin 2 tane uygulamasi vardir;

// mvc model1 -> eski yaklasim (1970 lerdeki masaustu icin uygulanan yaklasim.)

// ##### MVC model 2 yaklasimi;
// bu yaklasimda , onde bir tane Front Controller yer almaktadir!
// Istek geldiginde , gelen bu istek ilk olarak Front Controller tarafindna karsilanir!

// JSF te , FacesServlet implements Servlet 

// Spring MVCde public class DispatcherServlet extends FrameworkServlet { (yukarida HttpSevlet i kalitmakta)


// 1) Domain Layer
// Domain siniflarimiz , projeye ozgu siniflar.
// Account , Transaction, Customer...

// 2) User Interface layer
// uygulamayi son kullanaciya sunan yapi. response u client a goster.
// JSP , Excel , PDF , velocity. ...

// 3) Web Layer
// Controller kismina karsilik gelir. burada request mapping yapilmalidir.
// istek karsilanir (handle request) ve service layere gidilmelidir.
// controller tarafinda mumkun mertebe az kod olmalidir. burada business logic uygulanmamalidir!

// Spring MVCde Controller tanimlanamnin cesitli yontemleri vardir.
// org.springframework.stereotype.@Controller annotation 
// Spring te yeni versiyonlarda bir cok eski Controller sinifi ucuruldu!!!! DIKKAT! eski ornekleri kullanmak istersek dikkat edelim!
// org.springframework.web.servlet.mvc.Controller interfacesini de implement edebiliriz.
//

// 4) Service Layer
// uygulamanin yapacagi isler burada yer almaktadir. projeye ozgu kodlar.

// 5 ) Data Access Layer
// bu kisim database ile ilgili kisimdir.
// JDBC, JPA , Hibernate ...


//webcontent altýndaki dosyalata direk eriþim saglanabilir ama web-inf altýndaki dosyalara direk eriþim saglanamaz. bu yüzden jsp dosyalarý web-inf altýnda tutulur.
