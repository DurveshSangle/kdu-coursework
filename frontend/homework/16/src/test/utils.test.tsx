import { render } from '@testing-library/react'
import { Provider} from 'react-redux';
import type { RenderOptions } from '@testing-library/react'
import { RootState, Store } from '../redux/store';
import { PropsWithChildren } from 'react';

interface ExtendedRenderOptions extends Omit<RenderOptions, 'queries'> {
  preloadedState?: Partial<RootState>,
  store?: typeof Store
}

export function renderWithProviders(
    ui: React.ReactElement,
    {
      preloadedState = {},
      store=Store,
      ...renderOptions
    }: ExtendedRenderOptions={}
)
{
    function Wrapper({children}:Readonly<PropsWithChildren<object>>):JSX.Element{
        return <Provider store={Store}>{children}</Provider>
    }
    return {store,...render(ui,{wrapper:Wrapper,...renderOptions})}
}