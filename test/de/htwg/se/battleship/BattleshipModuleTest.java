package de.htwg.se.battleship;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class BattleshipModuleTest {

    private class TestBinder implements Binder {

        @SuppressWarnings("unchecked")
        @Override
        public <T> AnnotatedBindingBuilder<T> bind(Class<T> arg) {
            return (AnnotatedBindingBuilder<T>) new TestBinding<T>();
        }

        @Override
        public void addError(Throwable arg) {}
        @Override
        public void addError(Message arg) {}
        @Override
        public void addError(String arg0, Object... arg1) {}
        @Override
        public <T> LinkedBindingBuilder<T> bind(Key<T> arg) {return null;}
        @Override
        public <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> arg) {return null;}
        @Override
        public AnnotatedConstantBindingBuilder bindConstant() {return null;}
        @Override
        public void bindInterceptor(Matcher<? super Class<?>> arg0, Matcher<? super Method> arg1, MethodInterceptor... arg2) {}
        @Override
        public void bindListener(Matcher<? super TypeLiteral<?>> arg0, TypeListener arg1) {}
        @Override
        public void bindScope(Class<? extends Annotation> arg0, Scope arg1) {}
        @Override
        public void convertToTypes(Matcher<? super TypeLiteral<?>> arg0, TypeConverter arg1) {}
        @Override
        public Stage currentStage() {return null;}
        @Override
        public void disableCircularProxies() {}
        @Override
        public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> arg) {return null;}
        @Override
        public <T> MembersInjector<T> getMembersInjector(Class<T> arg) {return null;}
        @Override
        public <T> Provider<T> getProvider(Key<T> arg) {return null;}
        @Override
        public <T> Provider<T> getProvider(Class<T> arg) {return null;}
        @Override
        public void install(Module arg) {}
        @Override
        public PrivateBinder newPrivateBinder() {return null;}
        @Override
        public void requestInjection(Object arg) {}
        @Override
        public <T> void requestInjection(TypeLiteral<T> arg0, T arg1) {}
        @Override
        public void requestStaticInjection(Class<?>... arg) {}
        @Override
        public void requireExplicitBindings() {}
        @Override
        public Binder skipSources(@SuppressWarnings("rawtypes") Class... arg) {return null;}
        @Override
        public Binder withSource(Object arg) {return null;}
    }

    private class TestBinding<T> implements AnnotatedBindingBuilder<Object> {

        @Override
        public ScopedBindingBuilder to(Class<?> arg) {return null;}
        @Override
        public ScopedBindingBuilder to(TypeLiteral<?> arg) {return null;}
        @Override
        public ScopedBindingBuilder to(Key<?> ar) {return null;}
        @SuppressWarnings("rawtypes")
        @Override
        public ScopedBindingBuilder toConstructor(Constructor arg) {return null;}
        @SuppressWarnings("rawtypes")
        @Override
        public ScopedBindingBuilder toConstructor(Constructor arg0, TypeLiteral arg1) {return null;}
        @Override
        public void toInstance(Object arg) {}
        @Override
        public ScopedBindingBuilder toProvider(Provider<?> arg) {return null;}
        @SuppressWarnings("rawtypes")
        @Override
        public ScopedBindingBuilder toProvider(Class arg) {return null;}
        @SuppressWarnings("rawtypes")
        @Override
        public ScopedBindingBuilder toProvider(TypeLiteral arg0) {return null;}
        @SuppressWarnings("rawtypes")
        @Override
        public ScopedBindingBuilder toProvider(Key arg) {return null;}
        @Override
        public void asEagerSingleton() {}
        @Override
        public void in(Class<? extends Annotation> arg) {}
        @Override
        public void in(Scope arg) {}
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public LinkedBindingBuilder annotatedWith(Class arg) {return null;}
        @Override
        public LinkedBindingBuilder<Object> annotatedWith(Annotation arg0) {return null;}
    }

    @Test
    public void test() {
        AbstractModule am = new BattleshipModule();
        am.configure(new TestBinder());
    }
}