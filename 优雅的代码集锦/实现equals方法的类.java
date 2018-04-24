//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.hibernate.validator.internal.metadata.core;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.util.Set;
import org.hibernate.validator.internal.engine.ValidationContext;
import org.hibernate.validator.internal.engine.ValueContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.hibernate.validator.internal.metadata.location.ConstraintLocation;

public class MetaConstraint<A extends Annotation> {
    private final ConstraintTree<A> constraintTree;
    private final ConstraintDescriptorImpl<A> constraintDescriptor;
    private final ConstraintLocation location;

    public MetaConstraint(ConstraintDescriptorImpl<A> constraintDescriptor, ConstraintLocation location) {
        this.constraintTree = new ConstraintTree(constraintDescriptor);
        this.constraintDescriptor = constraintDescriptor;
        this.location = location;
    }

    public final Set<Class<?>> getGroupList() {
        return this.constraintDescriptor.getGroups();
    }

    public final ConstraintDescriptorImpl<A> getDescriptor() {
        return this.constraintDescriptor;
    }

    public final ElementType getElementType() {
        return this.constraintDescriptor.getElementType();
    }

    public boolean validateConstraint(ValidationContext<?> executionContext, ValueContext<?, ?> valueContext) {
        valueContext.setElementType(this.getElementType());
        valueContext.setDeclaredTypeOfValidatedElement(this.location.getTypeForValidatorResolution());
        boolean validationResult = this.constraintTree.validateConstraints(executionContext, valueContext);
        executionContext.markConstraintProcessed(valueContext.getCurrentBean(), valueContext.getPropertyPath(), this);
        return validationResult;
    }

    public ConstraintLocation getLocation() {
        return this.location;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            org.hibernate.validator.internal.metadata.core.MetaConstraint<?> that = (org.hibernate.validator.internal.metadata.core.MetaConstraint)o;
            if(this.constraintDescriptor != null) {
                if(!this.constraintDescriptor.equals(that.constraintDescriptor)) {
                    return false;
                }
            } else if(that.constraintDescriptor != null) {
                return false;
            }

            if(this.location != null) {
                if(this.location.equals(that.location)) {
                    return true;
                }
            } else if(that.location == null) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.constraintDescriptor != null?this.constraintDescriptor.hashCode():0;
        result = 31 * result + (this.location != null?this.location.hashCode():0);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetaConstraint");
        sb.append("{constraintType=").append(this.constraintDescriptor.getAnnotation().annotationType().getName());
        sb.append(", location=").append(this.location);
        sb.append("}");
        return sb.toString();
    }
}
