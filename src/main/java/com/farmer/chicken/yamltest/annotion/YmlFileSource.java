package com.farmer.chicken.yamltest.annotion;

import com.farmer.chicken.yamltest.provider.MissfreshFileProvider;
import org.apiguardian.api.API;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 * @Author: sulei
 * @Date: 2020-05-27 16:08
 * @Discription:
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(status = EXPERIMENTAL, since = "5.0")
@ParameterizedTest
@ArgumentsSource(MissfreshFileProvider.class)
public @interface YmlFileSource {

    String[] resources();

}
