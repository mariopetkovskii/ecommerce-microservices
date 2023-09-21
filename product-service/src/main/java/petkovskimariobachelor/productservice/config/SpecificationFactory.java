package petkovskimariobachelor.productservice.config;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class SpecificationFactory {

    public <T> Specification<T> like(String propertyName, String value) {
        if(value == null || value.isBlank())
            return Specification.where(null);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get(propertyName)), "%"+value.toLowerCase()+"%");
    }

    public <T> Specification<T> equal(String propertyName, Object value) {
        if(value == null)
            return Specification.where(null);
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(propertyName), value);
    }

    public <T> Specification<T> in(String propertyName, Collection<Long> values) {
        if(values == null || values.isEmpty()) {
            return Specification.where(null);
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(propertyName)).value(values);
    }

    public <T, V> Specification<T> intersect(String propertyName, Collection<V> values) {
        if(values == null || values.isEmpty())
            return Specification.where(null);
        return (root, query, criteriaBuilder) -> {
            var predicates = values.stream()
                    .map(value -> criteriaBuilder.like(root.get(propertyName).as(String.class), "%%%s%%".formatted(value)))
                    .toList();
            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        };
    }

    public <T> Specification<T> isNotNull(String propertyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(propertyName));
    }

    public <T> Specification<T> isNull(String propertyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(propertyName));
    }

    public <T> Specification<T> trueSpec() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and();
    }

    @SafeVarargs
    public final <T> Specification<T> and(Specification<T>... specifications) {
        return Arrays.stream(specifications)
                .reduce(Specification::and)
                .get();
    }

    @SafeVarargs
    public final <T> Specification<T> or(Specification<T>... specifications) {
        return Arrays.stream(specifications)
                .reduce(Specification::or)
                .get();
    }

}
