package jenkins.plugins.office365connector.helpers;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Arrays;
import java.util.Collection;

import hudson.model.User;
import hudson.scm.ChangeLogSet;
import hudson.scm.EditType;
import org.mockito.stubbing.Answer;

/**
 * @author Damian Szczepanik (damianszczepanik@github)
 */
public class AffectedFilesBuilder {

    private static final String DEVELOPER_NAME = "Mike";

    private class File implements ChangeLogSet.AffectedFile {

        @Override
        public String getPath() {
            return null;
        }

        @Override
        public EditType getEditType() {
            return null;
        }
    }

    public ChangeLogSet<? extends ChangeLogSet.Entry> sampleChangeLogSet() {
        ChangeLogSet.Entry entry = mock(ChangeLogSet.Entry.class);
        User user = mockUser();
        when(entry.getAuthor()).thenReturn(user);

        Collection<? extends ChangeLogSet.AffectedFile> files = Arrays.asList(new File(), new File());
        when(entry.getAffectedFiles()).thenAnswer(createAnswer(files));

        ChangeLogSet changeLogSet = mock(ChangeLogSet.class);
        when(changeLogSet.iterator()).thenAnswer(createAnswer(Arrays.asList(entry).iterator()));
        return changeLogSet;
    }

    private User mockUser() {
        User user = mock(User.class);
        when(user.toString()).thenReturn(DEVELOPER_NAME);
        return user;
    }

    public static <T> Answer<T> createAnswer(T value) {
        return (invocation -> value);
    }
}
