package info.rolandkrueger.userservice.api.resources;

import info.rolandkrueger.userservice.api._internal.AbstractPagedResource;
import info.rolandkrueger.userservice.api._internal.RestApiConstants;
import info.rolandkrueger.userservice.api.enums.AuthoritiesSort;
import info.rolandkrueger.userservice.api.enums.SortDirection;
import info.rolandkrueger.userservice.api.model.AuthorityApiData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

/**
 * Paged resource for {@link AuthorityApiData} objects.
 *
 * @author Roland Krüger
 */
public class AuthoritiesResource extends AbstractPagedResource<AuthorityApiData, AuthoritiesResource> implements
        CanCreate<AuthorityApiData>, CanUpdate<AuthorityApiData>, CanDelete<AuthorityApiData> {

    AuthoritiesResource(Link templatedBaseLink) {
        super(templatedBaseLink, templatedBaseLink);
    }

    public final AuthoritiesResource sort(AuthoritiesSort sortBy, SortDirection direction) {
        return sort(getSortByProperty(sortBy), direction);
    }

    public final AuthoritiesResource goToPageSorted(Integer page, Integer size, AuthoritiesSort sortBy, SortDirection
            direction) {
        return goToPageSorted(page, size, getSortByProperty(sortBy), direction);
    }

    /**
     * Navigates to the search resource for authority objects.
     *
     * @return the authority search resource
     */
    public final AuthoritiesSearchResource search() {
        return new AuthoritiesSearchResource(getLinkFor(getResponseEntity(), RestApiConstants.SEARCH_RESOURCE));
    }

    @Override
    protected AuthoritiesResource createResourceInstance(Link self) {
        return new AuthoritiesResource(self);
    }

    @Override
    protected Class<AuthorityApiData> getResourceType() {
        return AuthorityApiData.class;
    }

    @Override
    protected ParameterizedTypeReference<PagedResources<AuthorityApiData>> getParameterizedTypeReferencePaged() {
        return new ParameterizedTypeReference<PagedResources<AuthorityApiData>>() {
        };
    }

    @Override
    protected ParameterizedTypeReference<AuthorityApiData> getParameterizedTypeReference() {
        return new ParameterizedTypeReference<AuthorityApiData>() {
        };
    }

    @Override
    public ResponseEntity<AuthorityApiData> create(AuthorityApiData entity) throws RestClientException {
        return createInternal(entity);
    }

    @Override
    public void delete(AuthorityApiData entity) throws RestClientException {
        deleteInternal(entity);
    }

    @Override
    public void update(AuthorityApiData entity) throws RestClientException {
        updateInternal(entity);
    }

    /**
     * Null-safe helper method that reads the sort property from the given {@link AuthoritiesSort} enum.
     *
     * @param sortBy the sort property enum for authorities
     * @return the property to sort by or <code>null</code> if the given parameter is <code>null</code>
     */
    private String getSortByProperty(AuthoritiesSort sortBy) {
        return sortBy == null ? null : sortBy.getProperty();
    }
}
