package vlad.homework5.DTO.customer;

import lombok.Data;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

// справка:  https://stackoverflow.com/questions/28150405/validation-of-a-list-of-objects-in-spring
@Data
public class listCustomerDto<E> implements List<CustomerRequestDto>{

    @Valid
    private List<E> list;

    public listCustomerDto(){
        this.list = new ArrayList<>();
    }

    public listCustomerDto(List<E> list){
        this.list = list;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void replaceAll(UnaryOperator<CustomerRequestDto> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super CustomerRequestDto> c) {
        List.super.sort(c);
    }

    @Override
    public Spliterator<CustomerRequestDto> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super CustomerRequestDto> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<CustomerRequestDto> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<CustomerRequestDto> parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super CustomerRequestDto> action) {
        List.super.forEach(action);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Iterator<CustomerRequestDto> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(CustomerRequestDto customerRequestDto) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends CustomerRequestDto> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends CustomerRequestDto> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public CustomerRequestDto get(int index) {
        return null;
    }

    @Override
    public CustomerRequestDto set(int index, CustomerRequestDto element) {
        return null;
    }

    @Override
    public void add(int index, CustomerRequestDto element) {

    }

    @Override
    public CustomerRequestDto remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<CustomerRequestDto> listIterator() {
        return null;
    }

    @Override
    public ListIterator<CustomerRequestDto> listIterator(int index) {
        return null;
    }

    @Override
    public List<CustomerRequestDto> subList(int fromIndex, int toIndex) {
        return null;
    }
}
