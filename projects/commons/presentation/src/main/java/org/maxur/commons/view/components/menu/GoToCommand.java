package org.maxur.commons.view.components.menu;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.maxur.commons.view.components.model.Command;

/**
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public final class GoToCommand extends Command {

    private static final long serialVersionUID = 9012489431437576131L;

    private Class<? extends IRequestablePage> targetPage;

    private GoToCommand() {
        super(false);
    }

    @Override
    public void execute() {
        RequestCycle.get().setResponsePage(targetPage);
    }

    @Override
    public void execute(final AjaxRequestTarget target) {
        execute();
    }

    @Override
    public boolean isActive() {
        return getComponent() != null && this.targetPage.equals(getComponent().getPage().getClass());
    }

    public static final class Builder {

        private final GoToCommand result = new GoToCommand();

        public static Builder instance() {
            return new Builder();
        }

        private Builder() {
        }

        public Builder setAsTarget(final Class<? extends IRequestablePage> targetPage) {
            result.targetPage = targetPage;
            return this;
        }

        public Builder withTitleKey(final String key) {
            result.setTitleKey(key);
            return this;
        }

        public GoToCommand build() {
            if (result.getTitleKey() == null) {
                throw new AssertionError("The title key is not defined for this GoToCommand");
            }
            if (result.targetPage == null) {
                throw new AssertionError("The target page is not defined for this GoToCommand");
            }
            return result;
        }

    }

}
