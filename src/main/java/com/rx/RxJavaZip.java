package com.rx;

import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 */
public class RxJavaZip {
    private static Observable<List<String>> getStrings( String str1,  String str2) {
        return Observable.fromCallable(new Callable<List<String>>() {

            public List<String> call() {
                System.out.println("Processed in ThreadID==>"+Thread.currentThread().getName());
                List<String> strings = new ArrayList<>();
                strings.add(str1);
                strings.add(str2);
                return strings;
            }
        });
    }
    private static Func2<List<String>, List<String>, List<String>> mergeStringLists() {
        return new Func2<List<String>,  List<String>, List<String>>() {
            @Override
            public List<String> call(List<String> strings,  List<String> strings2) {


                for (String s : strings2) {
                    strings.add(s);
                }


                return strings;
            }
        };
    }
    public static void main(String[] arg)
    {

        Observable.zip( getStrings("One", "Two")
                        .subscribeOn(Schedulers.newThread()),
                getStrings("Three", "Four")
                        .subscribeOn(Schedulers.newThread()),
                mergeStringLists()).toBlocking().subscribe( result->{
            Observable.from(result).subscribe(str->{
               System.out.println(str);
            });

        });
    }
}
